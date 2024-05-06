import struct


class UltrashipU2v2(object):

    """Class for interfacing with USB-Serial version of UltraShip U2 scales.

    This handles reading / parsing packets sent by the scale when the "SEND"
    button is pushed.

    Note: It is not able to request data from the scale, or to get any more
    information than what is displayed on the top line of the display, and
    it's unknown whether those features might be possible with this scale.

    Version 1 of the scale used a USB HID interface; this class does not
    support that version.
    """

    def __init__(self, port):
        """Initialize the scale object."""
        port.baudrate = 9600
        self._port = port
        self._buf = ''

    def fill_buffer(self):
        c = self._port.read(14 - len(self._buf))
        self._buf += c

    @staticmethod
    def parse_packet(pkt):
        """Parse a scale packet & return the parsed value, or None if invalid.

        Args:
            pkt:  A 14-character string; should hold 1 packet of scale information.

        Returns:
            A string containing the packet's decoded contents, or None if the
            passed in array is not a valid packet.


        A properly formed packet is 14 bytes long.
         0: STX (0x02)
         1: XOR key for following bytes (needs to also be XORed with 0x26)
         2..10: Data Bytes
        11: Checksum High Byte
        12: Checksum Low Byte
        13: ETX (0x03)

        Checksum is calculated by simply adding together all of the bytes
        from index 1..10, before decoding with the key.
        """
        if len(pkt) >= 14:
            # The H is for the (big endian, 2-byte) checksum.
            data = struct.unpack('>BBBBBBBBBBBHB', pkt[0:14])

            if data[0] == 0x02 and data[12] == 0x03 and sum(data[1:11]) == data[11]:
                # Decode data bytes
                key = data[1] ^ 0x26
                # data[2] is a newline; toss it out.
                data = [ (data[i] ^ key) for i in range(3, 11) ]

                return struct.pack('BBBBBBBB', *data)

        return None

    def read(self):
        """Read a (decoded) packet's worth of data from the scale.

        This will keep reading bytes until it gets a valid packet, then will
        decode the packet and return the decoded contents (a string).
        """
        while True:
            self.fill_buffer()

            # Dump characters until find STX
            self._buf = self._buf[self._buf.find('\x02'):]

            while len(self._buf) >= 14:
                # Try to parse the packet... hopefully it's valid.
                result = self.parse_packet(self._buf)
                if result:
                    self._buf = self._buf[14:]
                    return result
                else:
                    # Bad packet.  Dump everything up to next STX and
                    # continue looking for a valid packet.
                    self._buf = self._buf[self._buf.find('\x02', 1):]