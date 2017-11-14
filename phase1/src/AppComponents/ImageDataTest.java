//package AppComponents;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.concurrent.TimeUnit;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//
//public class ImageInfoTest {
//
//    @Test
//    public void testEqualsFalse() {
//        ImageInfo p = new ImageInfo("SpringFields", "Vacation");
//        ImageInfo a = new ImageInfo("SpringFieldsRain", "Vacation");
//        assertFalse(p.equals(a));
//    }
//    @Test
//    public void testEqualsFalseTwo() {
//        ImageInfo p = new ImageInfo("SpringFields", "Vacation");
//        ImageInfo a = new ImageInfo("SpringFields", "Vacation");
//        assertFalse(p.equals(a));
//    }
//    @Test
//    public void testEqualsTrue() {
//        ImageInfo p = new ImageInfo("SpringFields", "Vacation");
//        ImageInfo a = p;
//        assertTrue(p.equals(a));
//    }
//
//
//    @Test
//    public void testImageInfoGetLocation() {
//        ImageInfo p = new ImageInfo("SpringFields", "Vacation");
//        assertEquals("Vacation", p.getImageLocation());
//    }
//
//    @Test
//    public void testImageInfoGetLocationTwo() {
//        ImageInfo p = new ImageInfo("SpringFields", "Vacation");
//        p.setImageLocation("Wallpaper");
//        assertEquals("Wallpaper", p.getImageLocation());
//    }
//
//    @Test
//    public void testImageInfoGetName() {
//        ImageInfo p = new ImageInfo("SpringFields", "Vacation");
//        assertEquals("SpringFields", p.getImageName());
//    }
//
//    @Test
//    public void testImageInfoGetNameTwo() {
//        ImageInfo p = new ImageInfo("SpringFields", "Vacation");
//        p.setImageName("SpringFields @Holland @2017");
//        assertEquals("SpringFields @Holland @2017", p.getImageName());
//    }
//
//
//    @Test
//    public void testImageInfoPrintLogInnit() {
//        ImageInfo p = new ImageInfo("SpringFields", "Vacation");
//        String time = p.getLastChangeTime();
//        String log = time + "---Initially named : SpringFields, initially in : Vacation"  + System.getProperty("line.separator");
//        assertEquals(log, p.printLog());
//    }
//
//    @Test
//    public void testImageInfoPrintLogLocation() throws InterruptedException {
//        ImageInfo p = new ImageInfo("SpringFields", "Vacation");
//        String time1 = p.getLastChangeTime();
//        TimeUnit.MILLISECONDS.sleep(1);
//        p.setImageLocation("Wallpaper");
//        String time2 = p.getLastChangeTime();
//        String log = time1 + "---Initially named : SpringFields, initially in : Vacation"  + System.getProperty("line.separator");
//        log += time2 + "---location change: Vacation --> Wallpaper" + System.getProperty("line.separator");
//        assertEquals(log, p.printLog());
//    }
//
//    @Test
//    public void testImageInfoPrintLogName() throws InterruptedException {
//        ImageInfo p = new ImageInfo("SpringFields", "Vacation");
//        String time1 = p.getLastChangeTime();
//        TimeUnit.MILLISECONDS.sleep(1);
//        p.setImageName("SpringFields @Holland @2017");
//        String time2 = p.getLastChangeTime();
//        String log = time1 + "---Initially named : SpringFields, initially in : Vacation"  + System.getProperty("line.separator");
//        log += time2 + "---tag change: SpringFields --> SpringFields @Holland @2017" + System.getProperty("line.separator");
//        assertEquals(log, p.printLog());
//    }
//
//    @Test
//    public void testImageInfoSetImageTags(){
//        ImageInfo p = new ImageInfo("SpringFields", "Vacation");
//        ArrayList<Tag> tags = new ArrayList<>();
//        Tag a = new Tag("HoLLaND");
//        Tag b = new Tag("2017");
//        Tag c = new Tag("Holland");
//        tags.add(a);
//        tags.add(b);
//        tags.add(c);
//        p.setImageTags(tags);
//        assertEquals("SpringFields @holland @2017", p.getImageName());
//    }
//
//    @Test
//    public void testImageInfoSetImageTagsTwo(){
//        ImageInfo p = new ImageInfo("SpringFields", "Vacation");
//        ArrayList<Tag> tags = new ArrayList<>();
//        Tag a = new Tag("HoLLaND");
//        Tag b = new Tag("2017");
//        Tag c = new Tag("Holland");
//        tags.add(a);
//        tags.add(b);
//        tags.add(c);
//        p.setImageTags(tags);
//        ArrayList<Tag> test = new ArrayList<>();
//        Tag aa = new Tag("holland");
//        Tag bb = new Tag("2017");
//        test.add(aa);
//        test.add(bb);
//        assertEquals(test, p.getImageTags());}


//}
