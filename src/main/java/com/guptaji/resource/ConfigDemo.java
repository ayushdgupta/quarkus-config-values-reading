package com.guptaji.resource;

import com.guptaji.config.CustomConfig;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/configDemo")
@Produces(MediaType.TEXT_PLAIN)
public class ConfigDemo {
    
//    @Inject
//    private CustomConfig customConfig;

    // Here if we will not define any property with key as 'made_easy' in application.properties then by default
    // 'Pratigya' will come in output.
    @ConfigProperty(name = "made_easy", defaultValue = "Pratigya")
    public String crushName;

//    @ConfigProperty(name = "all_crush_names", defaultValue = )
//    public List<String> allCrushNames;

    @GET
    public Response getConfigValues(){
        return Response.ok(crushName).build();
    }

    @GET
    @Path("/{crushKey}")
    public Response getConfigValues(@PathParam("crushKey") String crushKey){
        // in below syntax or process default option is not available so whenever some unknown value will pass
        // so error will be thrown
//        String crushNameByPlace = ConfigProvider.getConfig().getValue(crushKey+"_crush", String.class);

        // so let's use default as well
        String crushNameByPlace = ConfigProvider.getConfig()
                .getOptionalValue(crushKey+"_crush", String.class)
                .orElse("Pratigya");
        return Response.ok(crushNameByPlace).build();
    }

//    @GET
//    @Path("/getPrefixBasedConfigValues/{key}")
//    public Response getPrefixBased(@PathParam("key") String key){
//        return Response.ok(customConfig.dbKind + " "+ customConfig.userName).build();
//    }
}
