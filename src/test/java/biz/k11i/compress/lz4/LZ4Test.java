package biz.k11i.compress.lz4;

import java.nio.charset.Charset;
import org.junit.Test;

public class LZ4Test {

    @Test
    public void main() {
        LZ4Compressor comp = LZ4Codec.createCompressor();

        byte[] src = new byte[2048];
        byte[] dest = new byte[2048];

        for (int i = 0; i < 256; i++) {
            src[i] = (byte) i;
        }
        
        for (int i = 256; i < 700; i++) {
            src[i] = 30;
        }

        comp.compress(src, 1024, dest);
    }

    @Test
    public void checkCompressedAndDecompressedEquals() {
        final LZ4Compressor compressor = LZ4Codec.createCompressor();
        final LZ4Decompressor decompressor = LZ4Codec.createDecompressor();
        final byte[] src = "Hallo Welt".getBytes(Charset.forName("utf-8"));
        final byte[] dest = new byte[256];
        compressor.compress(src, src.length, dest);
        System.out.printf("dest.length=%d\n", dest.length);
        String string = new String(dest, Charset.forName("utf-8"));
        System.out.println(string);
    }
}
