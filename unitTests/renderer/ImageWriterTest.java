package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

import static org.junit.jupiter.api.Assertions.*;

class ImageWriterTest {

    @Test
    void getNy() {
    }

    @Test
    void getNx() {
    }

    @Test
    void writeToImage() {
        ImageWriter imageWriter = new ImageWriter("first",400,400);
        int linsx = 20;
        int linsy = 20;
        for (int i = 0; i<imageWriter.getNx() ; i++){
            for (int j =0;j<imageWriter.getNy() ;j++){
            if(i % linsx   == 0 || j %linsy == 0){
                imageWriter.writePixel(i,j, Color.BLACK);
                }
            else {
                imageWriter.writePixel(i,j, new Color(200,200,50));
                }

            }
        }
        imageWriter.writeToImage();
    }

    @Test
    void writePixel() {
    }
}