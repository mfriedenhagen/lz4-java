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
        final String input = "Hallo Welt Hallo Welt Hallo Welt Hallo Welt";
        final byte[] src = input.getBytes(Charset.forName("utf-8"));
        System.out.printf("src.length=%d\n", src.length);
        final byte[] dst = new byte[256];
        int compress = compressor.compress(src, dst);
        System.out.printf("dest.length=%d\n", dst.length);
        System.out.printf("compress=%d\n", compress);
        String string = new String(dst, 0, compress, Charset.forName("utf-8"));
        System.out.println("string=" + string);
        final byte[] out = new byte[2048];
        int decompress = decompressor.decompress(dst, compress, out);
        System.out.printf("decompress=%d\n", decompress);
    }
}
