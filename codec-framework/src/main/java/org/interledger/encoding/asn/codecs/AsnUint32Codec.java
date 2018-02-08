package org.interledger.encoding.asn.codecs;

/**
 * An ASN.1 codec for UInt32 objects that decodes them into {@link Long} values..
 */
public class AsnUint32Codec extends AsnOctetStringBasedObjectCodec<Long> {

  public AsnUint32Codec() {
    super(new AsnSizeConstraint(4));
  }

  @Override
  public Long decode() {
    byte[] bytes = getBytes();
    long value = 0;
    for (int i = 0; i <= 3; i++) {
      value <<= Byte.SIZE;
      value |= (bytes[i] & 0xFF);
    }
    return value;
  }

  @Override
  public void encode(Long value) {

    if (value > 4294967295L || value < 0) {
      throw new IllegalArgumentException(
          "Uint32 only supports values from 0 to 4294967295, value "
              + value + " is out of range.");
    }

    byte[] bytes = new byte[4];
    for (int i = 0; i <= 3; i++) {
      bytes[i] = ((byte) ((value >> (Byte.SIZE * (3 - i))) & 0xFF));
    }
    setBytes(bytes);
  }

  @Override
  public String toString() {
    return "AsnUint32Codec{"
        + "value=" + decode()
        + '}';
  }
}