//package AppComponents;
//
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//class ImageManagerTest {
//
//    @Test
//    void testImageManagerAddImage() {
//        ImageManager im = new ImageManager();
//        ImageData image1 = new ImageData("Toronto");
//        im.addImage(image1);
//
//        assertTrue(im.getImageList().size() == 1);
//        assertTrue(im.getImageList().contains(image1));
//    }
//
//    @Test
//    void testImageManagerGetImageList() {
//        ImageManager im = new ImageManager();
//        ImageData image1 = new ImageData("Toronto");
//        ImageData image2 = new ImageData("Toronto");
//        ImageData image3 = new ImageData("Toronto");
//        im.addImage(image1);
//        im.addImage(image2);
//        im.addImage(image3);
//
//        ArrayList<ImageData> testing1 = new ArrayList<>();
//        testing1.add(image1);
//        testing1.add(image2);
//        testing1.add(image3);
//
//        assertEquals(testing1, im.getImageList());
//    }
//
//}
