
package com.example.robospicetest;


import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.xml.XmlNamespaceDictionary;
import com.google.api.client.xml.XmlObjectParser;
import com.octo.android.robospice.request.googlehttpclient.GoogleHttpClientSpiceRequest;

public class XmlRequest extends GoogleHttpClientSpiceRequest<DataModel> {


    public XmlRequest() {
        super(DataModel.class);
    }

    @Override
    public DataModel loadDataFromNetwork() throws Exception {
        String url = String.format(
                "http://www.xmlfiles.com/examples/plant_catalog.xml");
        HttpRequest request = getHttpRequestFactory().buildGetRequest(new GenericUrl(url));
        request.setParser(new XmlObjectParser(new XmlNamespaceDictionary().set("", "")));
        HttpResponse htr= request.execute();
        DataModel data = htr.parseAs(getResultType());
        return data;
    }

    /**
     * This method generates a unique cache key for this request. In this case our cache key depends just on the
     * keyword.
     * 
     * @return
     */
    public String createCacheKey() {
        return "plants";
    }

}
