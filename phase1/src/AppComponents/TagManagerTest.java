package AppComponents;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TagManagerTest {

    @Test
    public void testAddTagSingle() {
        TagManager.addTag("tag1");
        assertTrue(TagManager.getListOfTags().size() == 1);
        assertEquals("tag1", TagManager.getListOfTags().get(0).getTagName());
        TagManager.getListOfTags().clear();
    }

    @Test
    public void testAddTagSingleCapitalizedTagName() {
        TagManager.addTag("TAG1");
        assertTrue(TagManager.getListOfTags().size() == 1);
        assertEquals("tag1", TagManager.getListOfTags().get(0).getTagName());
        TagManager.getListOfTags().clear();
    }

    @Test
    public void testTagExists() {
        TagManager.addTag("tag1");
        TagManager.addTag("tag2");
        assertTrue(TagManager.tagExists("tag1"));
        assertTrue(TagManager.tagExists("tag2"));
        TagManager.getListOfTags().clear();
    }

    @Test
    public void testTagExistsCapitalizedTagName() {
        TagManager.addTag("tag1");
        TagManager.addTag("tag2");
        assertTrue(TagManager.tagExists("tAG1"));
        assertTrue(TagManager.tagExists("Tag2"));
        TagManager.getListOfTags().clear();
    }

    @Test
    public void testAddTagArrayList() {
        ArrayList<String> tagNameList = new ArrayList<>(0);
        tagNameList.add("tag1");
        tagNameList.add("tag2");
        tagNameList.add("tag3");
        TagManager.addTag(tagNameList);
        assertTrue(TagManager.getListOfTags().size() == 3);
        assertTrue(TagManager.tagExists("tag1"));
        assertTrue(TagManager.tagExists("tag2"));
        assertTrue(TagManager.tagExists("tag3"));
        TagManager.getListOfTags().clear();
    }

    @Test
    public void testGetListOfTags() {
        TagManager.addTag("tag1");
        TagManager.addTag("tag2");
        assertTrue(TagManager.getListOfTags().size() == 2);
        assertEquals("tag1", TagManager.getListOfTags().get(0).getTagName());
        assertEquals("tag2", TagManager.getListOfTags().get(1).getTagName());
        TagManager.getListOfTags().clear();
    }

    @Test
    public void testGetTag() {
        TagManager.addTag("tag1");
        TagManager.addTag("tag2");
        assertEquals("tag1", TagManager.getTag("tag1").getTagName());
        assertEquals("tag2", TagManager.getTag("tag2").getTagName());
        TagManager.getListOfTags().clear();
    }

    @Test void testGetTagCapitalizedTagName() {
        TagManager.addTag("tag1");
        TagManager.addTag("tag2");
        assertEquals("tag1", TagManager.getTag("TAG1").getTagName());
        assertEquals("tag2", TagManager.getTag("tAg2").getTagName());
        TagManager.getListOfTags().clear();
    }

    @Test
    public void testGetNonExistentTag() {
        assertEquals(null, TagManager.getTag("tag"));
        TagManager.getListOfTags().clear();
    }

    @Test
    public void testGetImagesWithTag() {
        ImageInfo image = new ImageInfo("Studying with Ann", "Bahen");
        ImageInfo image2 = new ImageInfo("Studying with Mary", "Robarts");
        TagManager.addTag("Study");
        TagManager.getTag("Study").addImage(image);
        TagManager.getTag("Study").addImage(image2);
        assertEquals("Studying with Ann", TagManager.getImagesWithTag("study").get(0).getImageName());
        assertEquals("Studying with Mary", TagManager.getImagesWithTag("StuDy").get(1).getImageName());
        TagManager.getListOfTags().clear();
    }

}
