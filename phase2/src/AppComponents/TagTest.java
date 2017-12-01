package AppComponents;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TagTest {

    @Test
    void testTagGetName() {
        Tag breakfastTag = new Tag("breakfast");
        assertEquals("breakfast", breakfastTag.getTagName());
    }

    @Test
    void testTagGetAssociatedImages() {
        Tag breakfastTag = new Tag("breakfast");

        ImageData hamImage = new ImageData("C://Photo/Ham.jpg");
        ImageData baconImage = new ImageData("C://Photo/Bacon.jpg");
        ImageData cheeseImage = new ImageData("C://Photo/Cheese.jpg");

        breakfastTag.addImage(hamImage);
        breakfastTag.addImage(baconImage);
        breakfastTag.addImage(cheeseImage);

        assertTrue(breakfastTag.getAssociatedImages().size() == 3);

        ArrayList<ImageData> foodList = new ArrayList<>();
        foodList.add(hamImage);
        foodList.add(baconImage);
        foodList.add(cheeseImage);

        assertEquals(foodList, breakfastTag.getAssociatedImages());
    }

    @Test
    void testTagAddImage() {
        Tag hamTag = new Tag("ham");

        ImageData hamImage = new ImageData("C://Photo/Ham.jpg");

        hamTag.addImage(hamImage);
        assertTrue(hamTag.getAssociatedImages().size() == 1);
        assertTrue(hamTag.getAssociatedImages().contains(hamImage));
    }

    @Test
    void testTagRemoveImage() {
        Tag breakfastTag = new Tag("breakfast");

        ImageData hamImage = new ImageData("C://Photo/Ham.jpg");
        ImageData baconImage = new ImageData("C://Photo/Bacon.jpg");
        ImageData cheeseImage = new ImageData("C://Photo/Cheese.jpg");

        breakfastTag.addImage(hamImage);
        breakfastTag.addImage(baconImage);
        breakfastTag.addImage(cheeseImage);

        breakfastTag.removeImage(hamImage);
        assertFalse(breakfastTag.getAssociatedImages().contains(hamImage));
        breakfastTag.removeImage(baconImage);
        assertFalse(breakfastTag.getAssociatedImages().contains(baconImage));
        breakfastTag.removeImage(cheeseImage);
        assertFalse(breakfastTag.getAssociatedImages().contains(cheeseImage));
    }

    @Test
    void testTagEquals() {
        Tag ham = new Tag("ham");
        Tag ham2 = new Tag("Ham");
        Tag cheese = new Tag("cheese");
        assertEquals(ham, ham2);
        assertFalse(ham.equals(cheese));
    }

}
