package rabbitclientannotation;

import java.lang.annotation.*;

/**
 * @author Abishek Dulal on 2021-10-07
 */
@Target(ElementType.TYPE)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface MonkeyClient {
    String baseUrl();
    String service() default "";
}
