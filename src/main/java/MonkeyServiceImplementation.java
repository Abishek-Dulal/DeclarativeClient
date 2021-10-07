/**
 * @author Abishek Dulal on 2021-10-07
 */
public class MonkeyServiceImplementation {

    private String serviceName;
    private String baseUrl;
    private boolean fromServiceDiscovery;


    private String getServiceUrl(){
         if(serviceName==null || serviceName.isEmpty() || !fromServiceDiscovery ){
             return baseUrl;
         }
         return  doServiceDiscovery(serviceName);
    }


    private String doServiceDiscovery(String serviceName) {
        return null;
    }


}
