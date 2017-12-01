package AppComponents;

import AppGUI.MainContainer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TagManagerTest {

    @Test
    void testAddTagSingle() {
        ArrayList<String> tagNameList = new ArrayList<>();
        tagNameList.add("tag1");

        MainContainer.getAppTagManager().tmAddTagWithoutImage(tagNameList);
        assertTrue(MainContainer.getAppTagManager().getListOfTags().size() == 1);
        assertEquals("tag1", MainContainer.getAppTagManager().getListOfTags().get(0).getTagName());
        MainContainer.getAppTagManager().getListOfTags().clear();
    }

    @Test
    void testAddTagSingleCapitalizedTagName() {
        ArrayList<String> tagNameList = new ArrayList<>();
        tagNameList.add("TAG1");

        MainContainer.getAppTagManager().tmAddTagWithoutImage(tagNameList);
        assertTrue(MainContainer.getAppTagManager().getListOfTags().size() == 1);
        assertEquals("tag1", MainContainer.getAppTagManager().getListOfTags().get(0).getTagName());
        MainContainer.getAppTagManager().getListOfTags().clear();
    }

    @Test
    void testTagExists() {
        ArrayList<String> tagNameList = new ArrayList<>();
        tagNameList.add("tag1");
        tagNameList.add("tag2");

        MainContainer.getAppTagManager().tmAddTagWithoutImage(tagNameList);
        assertTrue(MainContainer.getAppTagManager().tagExists("tag1"));
        assertTrue(MainContainer.getAppTagManager().tagExists("tag2"));
        MainContainer.getAppTagManager().getListOfTags().clear();
    }

    @Test
    void testTagExistsCapitalizedTagName() {
        ArrayList<String> tagNameList = new ArrayList<>();
        tagNameList.add("tag1");
        tagNameList.add("tag2");

        MainContainer.getAppTagManager().tmAddTagWithoutImage(tagNameList);
        assertTrue(MainContainer.getAppTagManager().tagExists("tAG1"));
        assertTrue(MainContainer.getAppTagManager().tagExists("Tag2"));
        MainContainer.getAppTagManager().getListOfTags().clear();
    }

    @Test
    void testAddTagArrayList() {
        ArrayList<String> tagNameList = new ArrayList<>();
        tagNameList.add("tag1");
        tagNameList.add("tag2");
        tagNameList.add("tag3");

        MainContainer.getAppTagManager().tmAddTagWithoutImage(tagNameList);
        assertTrue(MainContainer.getAppTagManager().getListOfTags().size() == 3);
        assertTrue(MainContainer.getAppTagManager().tagExists("tag1"));
        assertTrue(MainContainer.getAppTagManager().tagExists("tag2"));
        assertTrue(MainContainer.getAppTagManager().tagExists("tag3"));
        MainContainer.getAppTagManager().getListOfTags().clear();
    }

    @Test
    void testGetListOfTags() {
        ArrayList<String> tagNameList = new ArrayList<>();
        tagNameList.add("tag1");
        tagNameList.add("tag2");

        MainContainer.getAppTagManager().tmAddTagWithoutImage(tagNameList);
        assertTrue(MainContainer.getAppTagManager().getListOfTags().size() == 2);
        assertEquals("tag1", MainContainer.getAppTagManager().getListOfTags().get(0).getTagName());
        assertEquals("tag2", MainContainer.getAppTagManager().getListOfTags().get(1).getTagName());
        MainContainer.getAppTagManager().getListOfTags().clear();
    }

    @Test
    void testGetTag() {
        ArrayList<String> tagNameList = new ArrayList<>();
        tagNameList.add("tag1");
        tagNameList.add("tag2");

        MainContainer.getAppTagManager().tmAddTagWithoutImage(tagNameList);
        assertEquals("tag1", MainContainer.getAppTagManager().getTag("tag1").getTagName());
        assertEquals("tag2", MainContainer.getAppTagManager().getTag("tag2").getTagName());
        MainContainer.getAppTagManager().getListOfTags().clear();
    }

    @Test
    void testGetTagCapitalizedTagName() {
        ArrayList<String> tagNameList = new ArrayList<>();
        tagNameList.add("tag1");
        tagNameList.add("tag2");

        MainContainer.getAppTagManager().tmAddTagWithoutImage(tagNameList);
        assertEquals("tag1", MainContainer.getAppTagManager().getTag("TAG1").getTagName());
        assertEquals("tag2", MainContainer.getAppTagManager().getTag("tAg2").getTagName());
        MainContainer.getAppTagManager().getListOfTags().clear();
    }

    @Test
    void testGetNonExistentTag() {
        assertEquals(null, MainContainer.getAppTagManager().getTag("tag"));
        MainContainer.getAppTagManager().getListOfTags().clear();
    }

//    @Test
//    public void testGetImagesWithTag() {
//        ImageData image = new ImageData("Studying with Ann", "Bahen");
//        ImageData image2 = new ImageData("Studying with Mary", "Robarts");
//        TagManager.addTag("Study");
//        TagManager.getTag("Study").addImage(image);
//        TagManager.getTag("Study").addImage(image2);
//        assertEquals("Studying with Ann", TagManager.getImagesWithTag("study").get(0).getImageName());
//        assertEquals("Studying with Mary", TagManager.getImagesWithTag("StuDy").get(1).getImageName());
//        TagManager.getListOfTags().clear();
//    }

}
