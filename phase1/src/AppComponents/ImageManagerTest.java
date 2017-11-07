package AppComponents;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ImageManagerTest {

    @Test
    void testImageManagerAddImage() {
        ImageManager im = new ImageManager();
        ImageInfo image1 = new ImageInfo("image1", "Toronto");
        im.addImage(image1);

        assertTrue(im.getImageList().size() == 1);
        assertTrue(im.getImageList().contains(image1));
    }

    @Test
    void testImageManagerGetImageList() {
        ImageManager im = new ImageManager();
        ImageInfo image1 = new ImageInfo("image1", "Toronto");
        ImageInfo image2 = new ImageInfo("image2", "Toronto");
        ImageInfo image3 = new ImageInfo("image3", "Toronto");
        im.addImage(image1);
        im.addImage(image2);
        im.addImage(image3);

        ArrayList<ImageInfo> testing1 = new ArrayList<>();
        testing1.add(image1);
        testing1.add(image2);
        testing1.add(image3);

        assertEquals(testing1, im.getImageList());
    }

}
