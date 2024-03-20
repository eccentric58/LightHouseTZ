import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Profile {
    public Long id;
    public Long orgId;
    public Long groupId;

    public Profile(Long id, Long orgId, Long groupId) {
        this.id = id;
        this.orgId = orgId;
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return String.format("{id: %d, orgId: %d, groupId: %d}", id, orgId, groupId);
    }

    public static Map<Long, Map<Long, List<Profile>>> groupByOrgIdAndGroupId(List<Profile> data) {
        Map<Long, Map<Long, List<Profile>>> result = new HashMap<>();

        for (Profile profile : data) {
            result.putIfAbsent(profile.orgId, new HashMap<>());
            Map<Long, List<Profile>> groupsMap = result.get(profile.orgId);
            groupsMap.putIfAbsent(profile.groupId, new ArrayList<>());
            groupsMap.get(profile.groupId).add(profile);
        }

        return result;
    }

    public static void main(String[] args) {
        List<Profile> profiles = new ArrayList<>();
        profiles.add(new Profile(1L, 0L, 1L));
        profiles.add(new Profile(2L, 0L, 1L));
        profiles.add(new Profile(3L, 0L, 2L));
        profiles.add(new Profile(4L, 1L, 1L));
        profiles.add(new Profile(5L, 1L, 2L));

        Map<Long, Map<Long, List<Profile>>> groupedProfiles = groupByOrgIdAndGroupId(profiles);
        System.out.println(groupedProfiles);
    }
}