package codes.LeetCode.may2020Challenges.week_1_1to7;

public class FirstBadVersion {
	
	int firstBadVersion= 5;
	
	public boolean isBadVersion(int version) {
		return version>=firstBadVersion;
	}
	
	public int firstBadVersion(int n) {
		int start = 1;
		int end = n;
		int badVersion = n;
		if(!isBadVersion(n)) {
			 return 0;
		} else {
			while(start<=end) {
				int mid = (start+end)/2;
				if(isBadVersion(mid)) {
					badVersion = mid;
					end = mid - 1;
				} else {
					start = mid + 1;
				}
			}
		}
        return badVersion;
    }
	
	public static void main(String[] args) {
		FirstBadVersion bv = new FirstBadVersion();
		System.out.println(bv.firstBadVersion(5));
	}

}
