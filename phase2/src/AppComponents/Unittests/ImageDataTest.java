package AppComponents.Unittests;

import AppComponents.ImageData;
import AppComponents.ImageLocation;
import AppComponents.Tag;
import AppGUI.MainContainer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ImageDataTest {

    @Test
    public void testDeleteTags() {
        ImageData dogeImage = new ImageData("C://Photos/Doge.jpg");
        Tag cuteTag = new Tag("cute");

        ArrayList<Tag> tagList = new ArrayList<>();
        tagList.add(cuteTag);

        dogeImage.addTags(tagList);

        assertTrue(dogeImage.hasTag("cute"));
        assertTrue(cuteTag.getAssociatedImages().contains(dogeImage));
        assertEquals("C://Photos/Doge @cute.jpg", dogeImage.getLocation());

        dogeImage.deleteTags(tagList);

        assertTrue(dogeImage.getImageTags().size() == 0);
        assertFalse(dogeImage.getImageTags().contains(cuteTag));
        assertEquals("C://Photos/Doge.jpg", dogeImage.getLocation());
    } // TODO: contains same code as testAddTags(); test addTags() before deleteTags()?

    @Test
    public void testAddTags() {
        ImageData dogeImage = new ImageData("C://Photos/Doge.jpg");
        Tag cuteTag = new Tag("cute");

        ArrayList<Tag> tagList = new ArrayList<>();
        tagList.add(cuteTag);

        dogeImage.addTags(tagList);

        assertTrue(dogeImage.hasTag("cute"));
        assertTrue(cuteTag.getAssociatedImages().contains(dogeImage));
        assertEquals("C://Photos/Doge @cute.jpg", dogeImage.getLocation());
    }

    @Test
    public void setImageTags() {
        ImageData dogeImage = new ImageData("C://Photos/Doge.jpg");
        Tag cuteTag = new Tag("cute");

        ArrayList<Tag> tagList = new ArrayList<>();
        tagList.add(cuteTag);

        dogeImage.setImageTags(tagList);

        assertTrue(dogeImage.hasTag("cute"));
        assertTrue(MainContainer.getAppTagManager().tagExists("cute"));
        assertEquals("C://Photos/Doge @cute.jpg", dogeImage.getLocation());
    }

    @Test
    public void testSetImageName() {
        ImageData dogeImage = new ImageData("C://Photos/Doge.jpg");

        dogeImage.setImageName("Doge @cute @dog");

        assertEquals("C://Photos/Doge @cute @dog.jpg", dogeImage.getLocation());
    }

    @Test
    public void testContainsTags() {
        ImageData dogeImage = new ImageData("C://Photos/Doge.jpg");
        Tag cuteTag = new Tag("cute");

        ArrayList<Tag> tagList = new ArrayList<>();
        tagList.add(cuteTag);
        dogeImage.addTags(tagList);

        ArrayList<String> tagNameListTrue = new ArrayList<>();
        tagNameListTrue.add("cute");
        ArrayList<String> tagNameListFalse = new ArrayList<>();
        tagNameListFalse.add("dog");

        assertTrue(dogeImage.containsTags(tagNameListTrue));
        assertFalse(dogeImage.containsTags(tagNameListFalse));
    }

    @Test
    public void testHasTag() {
        ImageData dogeImage = new ImageData("C://Photos/Doge.jpg");
        Tag cuteTag = new Tag("cute");

        ArrayList<Tag> tagList = new ArrayList<>();
        tagList.add(cuteTag);
        dogeImage.addTags(tagList);

        assertTrue(dogeImage.hasTag("cute"));
        assertFalse(dogeImage.hasTag("dog"));
    }

    @Test
    public void testEquals() {
        ImageData dogeImage = new ImageData("C://Photos/Doge.jpg");
        ImageData dogeImage2 = new ImageData("C://Photos/Doge.jpg");

        assertTrue(dogeImage.equals(dogeImage2));
    }

    @Test
    public void testGetName() {
        ImageData dogeImage = new ImageData("C://Photos/Doge.jpg");
        assertEquals("Doge", dogeImage.getName());
    }

    @Test
    public void testGetCoreName() {
        ImageData dogeImage = new ImageData("C://Photos/Doge.jpg");
        assertEquals("Doge", dogeImage.getCoreName());
    }

    @Test
    public void testGetLocation() {
        ImageData dogeImage = new ImageData("C://Photos/Doge.jpg");
        assertEquals("C://Photos/Doge.jpg", dogeImage.getLocation());
    }

    @Test
    public void testGetImageTags() {
        ImageData dogeImage = new ImageData("C://Photos/Doge.jpg");
        Tag cuteTag = new Tag("cute");

        ArrayList<Tag> tagList = new ArrayList<>();
        tagList.add(cuteTag);
        dogeImage.addTags(tagList);

        assertEquals(tagList, dogeImage.getImageTags());
    }

    @Test void testGetImageLocation() {
        ImageData dogeImage = new ImageData("C://Photos/Doge.jpg");

        ImageLocation dogeLocation = new ImageLocation("C://Photos/Doge.jpg");

        assertEquals(dogeLocation, dogeImage.getImageLocation());
    }
}
