import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Search {
	private static final String NEW_LINE = "\n";
	private static final int INF = 200_001;
	
	private static POI[] point = null;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		point = new POI[n + 1];
		point[n] = new POI(INF, INF);
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			point[i] = new POI(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
		}
		
		Arrays.sort(point);
		
		int half = n / 2;
		int m = Integer.parseInt(br.readLine());
		Radian[] info = new Radian[m];
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			info[i] = new Radian(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
			
			double minDist = info[i].x - info[i].r - 1;
			double maxDist = info[i].x + info[i].r + 1;
			
			int counts = 0;
			
			int lowerBound = binarySearch(0, half, 0, minDist);
			int upperBound = binarySearch(half, n, 0, maxDist, n);
			
			lowerBound = lowerBound <= 0 ? lowerBound : lowerBound - 1;
			upperBound = upperBound >= n - 1 ? upperBound : upperBound + 1;
			
			for(int idx = lowerBound; idx < upperBound + 1; idx++) {
				if(Math.pow((point[idx].x - info[i].x), 2) + Math.pow((point[idx].y - info[i].y), 2) <= Math.pow(info[i].r, 2)) counts++;
			}
			
			sb.append(counts).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static class POI implements Comparable<POI> {
		double x;
		double y;
		
		public POI(double x, double y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(POI poi) {			
			if(this.x < poi.x) return -1;
			else if(this.x > poi.x) return 1;
			else return 0;
		}
	}
	
	private static class Radian{
		double x;
		double y;
		double r;
		
		public Radian(double x, double y, double r) {
			this.x = x;
			this.y = y;
			this.r = r;
		}
	}
	
	private static int binarySearch(int left, int right, int mid, double limit) {
		int result = 0;
		
		while(left < right) {
			mid = (left + right) / 2;
			
			if(point[mid].x == limit) {
				return result;
			}
			else if(point[mid].x < limit) {
				result = Math.max(mid, result);
				left = mid + 1;
			}
			else {
				right = mid - 1;
			}
		}
		
		return result;
	}
	
	private static int binarySearch(int left, int right, int mid, double limit, int N) {
		int result = N - 1;
		
		while(left < right) {
			mid = (left + right) / 2;
			
			if(point[mid].x == limit) {
				return result;
			}
			else if(point[mid].x < limit) {
				left = mid + 1;
			}
			else {
				result = Math.min(result, mid);
				right = mid - 1;
			}
		}
		
		return result;
	}
}
