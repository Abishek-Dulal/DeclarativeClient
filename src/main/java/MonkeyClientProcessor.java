import com.work.service.MonkeyService;
import rabbitclientannotation.MonkeyClient;

import java.io.IOException;
import java.lang.annotation.Annotation;

/**
 * @author Abishek Dulal on 2021-10-07
 */
public class MonkeyClientProcessor {

    public void process() throws IOException {
        var mtoServiceClass = MonkeyService.class;
        var annotations = mtoServiceClass.getAnnotations();
        for (Annotation annotation :annotations) {
            if(annotation.annotationType().equals(MonkeyClient.class)){
                 MonkeyClient rabbitClient=(MonkeyClient) annotation;
                 MonkeyClientImplementationBuilder rabbitClientImplementationBuilder = new MonkeyClientImplementationBuilder();
                 rabbitClientImplementationBuilder.build(mtoServiceClass,rabbitClient,false);
            }
        }
    }

}
