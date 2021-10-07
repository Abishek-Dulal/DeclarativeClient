package com.work.service;

import rabbitclientannotation.*;

/**
 * @author Abishek Dulal on 2021-10-07
 */
@MonkeyClient(
        baseUrl ="www.google.com",
        service= ""
)
public interface MonkeyService {

     @Get(url= "")
     void doSomething();

     @Post(url="")
     @Headers(header = {
             @Header(key = "", value = ""),
             @Header(key = "",value = "")
     })
     Object getMonkeyData(Object object);


}
