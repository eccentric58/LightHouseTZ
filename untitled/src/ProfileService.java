import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ProfileService {
    public static class ProfileInfo {
        public UserInfo userInfo;
        public CompanyInfo companyInfo;
    }

    public static class UserInfo {
        public String name;
        public String age;
    }

    public static class CompanyInfo {
        public String id;
        public String companyName;
    }

    public ProfileInfo getProfileInfo(Long id) {
        CompletableFuture<UserInfo> userInfoFuture = CompletableFuture.supplyAsync(() -> getUserInfo(id));
        CompletableFuture<CompanyInfo> companyInfoFuture = CompletableFuture.supplyAsync(() -> getCompanyInfo(id));

        ProfileInfo profileInfo = new ProfileInfo();
        try {
            CompletableFuture.allOf(userInfoFuture, companyInfoFuture).get(1, TimeUnit.SECONDS);
            profileInfo.userInfo = userInfoFuture.get();
            profileInfo.companyInfo = companyInfoFuture.get();
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
        return profileInfo;
    }

    private UserInfo getUserInfo(Long id) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        UserInfo userInfo = new UserInfo();
        return userInfo;
    }

    private CompanyInfo getCompanyInfo(Long id) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CompanyInfo companyInfo = new CompanyInfo();
        return companyInfo;
    }
}