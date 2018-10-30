package com.theta.animationdemo.retrofit.webservices;

import com.theta.animationdemo.retrofit.model.GetInstruction;
import com.theta.animationdemo.retrofit.model.MlsResponse;
import com.theta.animationdemo.retrofit.model.StoreModel;
import com.theta.animationdemo.retrofit.model.StoreModelRequest;
import com.theta.animationdemo.retrofit.model.UpdateProfile;
import com.theta.animationdemo.retrofit.model.VerifyUser;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

import static com.theta.animationdemo.retrofit.webservices.Api.AUTH_BASE_URL;
import static com.theta.animationdemo.retrofit.webservices.Api.updateProfile;

/**
 * Created by ashish on 26/4/18.
 */

public interface ApiInterface {

    // TODO: 26/4/18 to add dynamic headers
   /* @Headers({
            "Accept: application/json"
    })*/

    // TODO: 4/5/18  GetStore api call interface
    @POST(Api.ActionGetStore)
    Call<StoreModel> getStoreModel(@Body StoreModelRequest storeModelRequest);

    // TODO: 4/5/18  GetInstruction api call interface
    @POST(Api.ActionGetInstruction)
    Call<GetInstruction> getInstructionModel();

    // TODO: 4/5/18 Pagination Api
    @GET("getL4DataFromL3?id=5805a572f8c5e83a5113ccb8&userId=mohanlalsons2016@gmail.com&brand=58049247f8c5e8346a3ac387&size=&price=&color=&offer=&sort_type=\"relevance\"")
    Call<MlsResponse> callMlsApi(@Query("page") int page);

    // TODO: 4/5/18 api call interface for updateprofile with header part static
    @Headers({
            "AUTHORIZATION: bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MjU2MzM5MjgsInVzZXJfbmFtZSI6Ijk4OTgxNzg3NjkiLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianRpIjoiYzQxZGRiM2ItZDcwNC00ZDRlLWFkMGYtYzQ1MGJlZTJjMjFhIiwiY2xpZW50X2lkIjoiY2xpZW50SWQiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXX0.seHhcJz1aBW0Qj5tQV30U_aNuqvBf55jBD4CDAfJ58w"
    })
    @Multipart
    @POST(updateProfile)
    Call<UpdateProfile> Upload(@Part("file") RequestBody file,
                               @Part("firstName") RequestBody description1,
                               @Part("lastName") RequestBody description,
                               @Part("emailId") RequestBody description2);


    // TODO: 3/5/18 only pass the endpoint as "/user/updateProfile" and with dynamic header using RequestBody
    @Multipart
    @POST(updateProfile)
    Call<UpdateProfile> Upload1(@Header("Authorization") String token, @Part("file") RequestBody file,
                                @Part("firstName") RequestBody description1,
                                @Part("lastName") RequestBody description,
                                @Part("emailId") RequestBody description2);

    // TODO: 5/5/18 upload image using MultipartBody
    @Headers({
            "AUTHORIZATION: bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MjU2MzM5MjgsInVzZXJfbmFtZSI6Ijk4OTgxNzg3NjkiLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianRpIjoiYzQxZGRiM2ItZDcwNC00ZDRlLWFkMGYtYzQ1MGJlZTJjMjFhIiwiY2xpZW50X2lkIjoiY2xpZW50SWQiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXX0.seHhcJz1aBW0Qj5tQV30U_aNuqvBf55jBD4CDAfJ58w"
    })
    @Multipart
    @POST(updateProfile)
    Call<UpdateProfile> updateProfileMultipart(@Part MultipartBody.Part file,
                                               @Part("firstName") RequestBody firstName,
                                               @Part("lastName") RequestBody lastName,
                                               @Part("emailId") RequestBody emailId);

    // TODO: 5/5/18 upload image using MultipartBody with map
    @Headers({
            "AUTHORIZATION: bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MjU2MzM5MjgsInVzZXJfbmFtZSI6Ijk4OTgxNzg3NjkiLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianRpIjoiYzQxZGRiM2ItZDcwNC00ZDRlLWFkMGYtYzQ1MGJlZTJjMjFhIiwiY2xpZW50X2lkIjoiY2xpZW50SWQiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXX0.seHhcJz1aBW0Qj5tQV30U_aNuqvBf55jBD4CDAfJ58w"
    })
    @Multipart
    @POST("upload")
    Call<UpdateProfile> uploadFileWithPartMap(
            @PartMap() Map<String, RequestBody> partMap,
            @Part MultipartBody.Part file);


    // TODO: 5/5/18 upload multiple images
    @Multipart
    @POST("upload")
    Call<ResponseBody> uploadMultipleFiles(
            @Part("description") RequestBody description,
            @Part MultipartBody.Part file1,
            @Part MultipartBody.Part file2);

    @Multipart
    @POST("upload")
    Call<ResponseBody> uploadMultipleFilesDynamic(
            @Part("description") RequestBody description,
            @Part List<MultipartBody.Part> files);

    // ##################################### oauth/token api ######################################################
    // TODO: 3/5/18   pass dynamic url  using "//13.126.18.81:8081/oauth-server/oauth/token"
    @Headers({
            "Authorization: Basic Y2xpZW50SWQ6Y2xpZW50X3NlY3JldA=="
    })
    @Multipart
    @POST(AUTH_BASE_URL)
    Call<VerifyUser> verifyUserCall(@Part("grant_type") RequestBody grant_type,
                                    @Part("username") RequestBody username,
                                    @Part("password") RequestBody password);


    // TODO: 3/5/18 pass dynamic url as "http://13.126.18.81:8081/oauth-server/oauth/token";
    @Headers({
            "Authorization: Basic Y2xpZW50SWQ6Y2xpZW50X3NlY3JldA=="
    })
    @Multipart
    @POST()
    Call<VerifyUser> verifyUserCallDynamicUrl(@Url String url,
                                              @Part("grant_type") RequestBody grant_type,
                                              @Part("username") RequestBody username,
                                              @Part("password") RequestBody password);


//##########################################################################

    @POST(Api.ActionGetStore)
    Observable<StoreModel> getStoreModel1(@Body StoreModelRequest storeModelRequest);
}
