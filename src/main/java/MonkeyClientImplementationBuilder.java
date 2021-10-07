import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import org.apache.http.client.HttpClient;
import rabbitclientannotation.MonkeyClient;

import javax.lang.model.element.Modifier;
import java.io.IOException;

/**
 * @author Abishek Dulal on 2021-10-07
 */
public class MonkeyClientImplementationBuilder {

    public void build(Class<?> clz, MonkeyClient client, Boolean fromServiceDiscovery) throws IOException {
        TypeSpec.Builder className = buildClassName(clz);
        generateClassFields(className,client,fromServiceDiscovery);

         MethodSpec getServiceUrl = generateGetServiceUrl();
         className.addMethod(getServiceUrl);

         MethodSpec doServiceDiscovery = generateDoServiceDiscovery();
         className.addMethod(doServiceDiscovery);

         MethodSpec methodSpec = generateGetHttpClient();
         className.addMethod(methodSpec);

         String packageName = getPackageName(clz);
         generateClassFile(packageName ,className.build());

    }

    private MethodSpec generateGetHttpClient() {
        return MethodSpec.methodBuilder("getHttpClient")
                .returns(HttpClient.class)
                .addModifiers(Modifier.PRIVATE)
                .build();
    }

    private MethodSpec generateDoServiceDiscovery() {
        return MethodSpec.methodBuilder("doServiceDiscovery")
                .returns(String.class)
                .addParameter(String.class,"serviceName")
                .addModifiers(Modifier.PRIVATE)
                .addCode("return null;")
                .build();
    }

    private MethodSpec generateGetServiceUrl() {
        return MethodSpec.methodBuilder("getServiceUrl")
                .addModifiers(Modifier.PRIVATE)
                .addParameter(String.class,"serviceName")
                .returns(String.class)
                .addCode(" if(serviceName==null || serviceName.isEmpty() || !fromServiceDiscovery ){\n" +
                        "      return baseUrl;\n" +
                        "   }\n" +
                        " return  doServiceDiscovery(serviceName);")
                .build();
    }


    private void generateClassFields(TypeSpec.Builder className, MonkeyClient client, Boolean fromServiceDiscovery) {
        var baseUrl = FieldSpec.builder(String.class, "baseUrl", Modifier.PRIVATE)
                .initializer("$S",client.baseUrl()).build();

        var serviceName = FieldSpec.builder(String.class, "serviceName", Modifier.PRIVATE)
                .initializer("$S",client.service()).build();

        var fromService = FieldSpec.builder(Boolean.class, "fromServiceDiscovery", Modifier.PRIVATE)
                .initializer(fromServiceDiscovery.toString()).build();

        className.addField(baseUrl)
                 .addField(serviceName)
                 .addField(fromService);
    }

    private void generateClassFile(String packageName, TypeSpec classType) throws IOException {
        JavaFile javaFile = JavaFile.builder(packageName, classType)
                .build();
        javaFile.writeTo(System.out);
    }

    private String getPackageName(Class<?> clz) {
        return clz.getPackageName();
    }

    private TypeSpec.Builder buildClassName(Class<?> clz) {
        var simpleName = clz.getSimpleName();
        return TypeSpec.classBuilder(simpleName+"Implementation");
    }



}
