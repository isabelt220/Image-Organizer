package AppComponents;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ImageInfoTest {
    ImageInfo p = new ImageInfo("SpringFields", "Vacation");
    @Test
    public void testImageInfoGetID() {
        ImageInfo p = new ImageInfo("SpringFields", "Vacation");
        ImageInfo a = new ImageInfo("SpringFieldsRain", "Vacation");
        assertEquals(1, p.getImageID());
        assertEquals(2, a.getImageID());
    }

    @Test
    public void testImageInfoGetLocation() {
        ImageInfo p = new ImageInfo("SpringFields", "Vacation");
        assertEquals("Vacation", p.getImageLocation());
    }

    @Test
    public void testImageInfoGetLocationTwo() {
        ImageInfo p = new ImageInfo("SpringFields", "Vacation");
        p.setImageLocation("Wallpaper");
        assertEquals("Wallpaper", p.getImageLocation());
    }

    @Test
    public void testImageInfoGetName() {
        ImageInfo p = new ImageInfo("SpringFields", "Vacation");
        assertEquals("SpringFields", p.getImageName());
    }

    @Test
    public void testImageInfoGetNameTwo() {
        ImageInfo p = new ImageInfo("SpringFields", "Vacation");
        p.setImageName("SpringFields @Holland @2017");
        assertEquals("SpringFields @Holland @2017", p.getImageName());
    }


    @Test
    public void testImageInfoPrintLogInnit() {
        String time = p.getLastChangeTime();
        String log = time + "---Initially named : SpringFields, initially in : Vacation"  + System.getProperty("line.separator");
        assertEquals(log, p.printLog());
    }

    @Test
    public void testImageInfoPrintLogLocation() {
        String time1 = p.getLastChangeTime();
        System.out.println(time1);
        p.setImageLocation("Wallpaper");
        String time2 = p.getLastChangeTime();
        System.out.println(time2);
        String log = time1 + "---Initially named : SpringFields, initially in : Vacation"  + System.getProperty("line.separator");
        log += time2 + "---location change: Vacation --> Wallpaper" + System.getProperty("line.separator");
        assertEquals(log, p.printLog());
    }

    @Test
    public void testImageInfoPrintLogName() {
        String time1 = p.getLastChangeTime();
        p.setImageName("SpringFields @Holland @2017");
        String time2 = p.getLastChangeTime();
        String log = time1 + "---Initially named : SpringFields, initially in : Vacation"  + System.getProperty("line.separator");
        log += time2 + "---tag change: SpringFields --> SpringFields @Holland @2017" + System.getProperty("line.separator");
        assertEquals(log, p.printLog());
    }


}
