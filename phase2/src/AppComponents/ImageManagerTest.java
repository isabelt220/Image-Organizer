package AppComponents;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ImageManagerTest {

    @Test
    void testGetImageList() {
        ImageManager im = new ImageManager();

        ImageData dogeImage = new ImageData("C://Photos/Doge.jpg");
        ImageData pepeImage = new ImageData("C://Photos/Pepe.jpg");
        ImageData grumpyCatImage = new ImageData("C://Photos/Grumpy Cat.jpg");

        ArrayList<ImageData> imList = new ArrayList<>();
        imList.add(dogeImage);
        imList.add(pepeImage);
        imList.add(grumpyCatImage);

        im.setImageList(imList);

        assertTrue(im.getImageList().size() == 3);
        assertTrue(im.getImageList().contains(dogeImage));
        assertTrue(im.getImageList().contains(pepeImage));
        assertTrue(im.getImageList().contains(grumpyCatImage));
    }

    @Test
    void testImAddTagWithImage() {
        ImageManager im = new ImageManager();

        ImageData dogeImage = new ImageData("C://Photos/Doge.jpg");

        ArrayList<ImageData> imList = new ArrayList<>();
        imList.add(dogeImage);
        im.setImageList(imList);

        String tagName = "cute";
        ArrayList<String> tagNameList = new ArrayList<>();
        tagNameList.add(tagName);

        im.imAddTagWithImage(dogeImage, tagNameList);
        // TODO: remove print statement
        System.out.println(im.getImageList().get(0).getLocation());
        assertTrue(im.getImage("C://Photos/Doge @cute.jpg").hasTag("cute"));
    }

    @Test
    void testGetImage() {
        ImageManager im = new ImageManager();

        ImageData dogeImage = new ImageData("C://Photos/Doge.jpg");

        ArrayList<ImageData> imList = new ArrayList<>();
        imList.add(dogeImage);
        im.setImageList(imList);

        assertTrue(im.getImage("C://Photos/Doge.jpg").equals(dogeImage));
    }

    @Test
    void testImageExists() {
        ImageManager im = new ImageManager();

        ImageData dogeImage = new ImageData("C://Photos/Doge.jpg");

        ArrayList<ImageData> imList = new ArrayList<>();
        imList.add(dogeImage);
        im.setImageList(imList);

        assertTrue(im.imageExists("C://Photos/Doge.jpg"));
    }

    @Test
    void testImAddTagNewImage() {
        ImageManager im = new ImageManager();

        ImageData dogeImage = new ImageData("C://Photos/Doge.jpg");

        String tagName = "cute";
        ArrayList<String> tagNameList = new ArrayList<>();
        tagNameList.add(tagName);

        // imAddTagNewImage() has private access and can only be accessed
        // through calling imAddTagWithImage()
        im.imAddTagWithImage(dogeImage, tagNameList);
        assertTrue(im.getImage("C://Photos/Doge @cute.jpg").hasTag("cute"));
    }

    @Test
    void testRemoveTagFromAppAndImages() {
        // Instantiate managers and objects
        ImageManager im = new ImageManager();
        ImageData dogeImage = new ImageData("C://Photos/Doge.jpg");
        TagManager tm = new TagManager();

        // Add dogeImage to ImageManager list of images
        ArrayList<ImageData> imList = new ArrayList<>();
        imList.add(dogeImage);
        im.setImageList(imList);

        // Add a tag to TagManager list of tags
        ArrayList<String> tagNameList = new ArrayList<>();
        tagNameList.add("cute");
        tm.tmAddTagWithoutImage(tagNameList);

        // Add new tag to dogeImage
        ArrayList<Tag> tagList = new ArrayList<>();
        tagList.add(tm.getTag("cute"));
        // TODO: remove print statement
        System.out.println(im.getImageList().get(0).getLocation());
        im.getImage("C://Photos/Doge.jpg").addTags(tagList);

        // Testing
        im.removeTagFromAppAndImages("cute");
        assertFalse(im.getImage("C://Photos/Doge @cute.jpg").hasTag("cute"));
        assertFalse(tm.tagExists("cute"));
    }

    @Test
    void testRemoveTagFromPic() {
        // Instantiate managers and objects
        ImageManager im = new ImageManager();
        ImageData dogeImage = new ImageData("C://Photos/Doge.jpg");
        TagManager tm = new TagManager();

        // Add dogeImage to ImageManager list of images
        ArrayList<ImageData> imList = new ArrayList<>();
        imList.add(dogeImage);
        im.setImageList(imList);

        // Add a tag to TagManager list of tags
        ArrayList<String> tagNameList = new ArrayList<>();
        tagNameList.add("cute");
        tm.tmAddTagWithoutImage(tagNameList);
        // TODO: remove print statement
        System.out.println(tm.getListOfTags());
        System.out.println(tm.getObservableTagList());


        // Add new tag to dogeImage
        ArrayList<Tag> tagList = new ArrayList<>();
        tagList.add(tm.getTag("cute"));
        // TODO: remove print statement
        System.out.println(tagList.get(0).getTagName());
        System.out.println(im.getImageList().get(0).getLocation());
        im.getImage("C://Photos/Doge.jpg").addTags(tagList);
        System.out.println(im.getImageList().get(0).getLocation());

        // Testing
        im.removeTagFromPic(tagList, dogeImage);
        // TODO: remove print statement
        System.out.println(im.getImageList().get(0).getLocation());
        System.out.println(im.getImageList().get(0).getImageTags());
        assertFalse(im.getImage("C://Photos/Doge.jpg").hasTag("cute"));
        assertTrue(tm.tagExists("cute"));
        assertFalse(tm.getTag("cute").getAssociatedImages().contains(dogeImage));
    }

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

}
