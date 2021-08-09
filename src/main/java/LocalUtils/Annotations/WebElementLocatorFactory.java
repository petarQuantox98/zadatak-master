package LocalUtils.Annotations;

import LocalUtils.UtilsBaseClass;

import java.lang.reflect.Method;


public class WebElementLocatorFactory extends UtilsBaseClass {

    public WebElementLocatorFactory() {
    }

    public String getLocator(Class c, String webElementName) {

        Method m = null;
        try {

            m = c.getDeclaredMethod(webElementName);
            m.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        String locator = null;

        if (m.isAnnotationPresent(WebElementLocator.class)) {
            WebElementLocator ta = m.getAnnotation(WebElementLocator.class);
            switch (getPlatform()) {
                case "webDesktop":
                    locator = ta.webDesktop();
                    break;

                case "webPhone":
                    locator = ta.webPhone();
                    break;

                default:
                    throw new IllegalStateException("Unexpected value: " + getPlatform());
            }
        }

        return locator;
    }

}






