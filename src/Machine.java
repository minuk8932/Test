import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Machine {
	private static final int INF = 10_000_000;
	private static final String EMPTY = "";
	private static final String NO_ANSWER = "NO ANSWER";
	
	private static final char DIVIDE2 = 'D';
	private static final char MULTIPLY2 = 'M';
	private static final char SORT = 'S';
	private static final char TROS = 'T';
	
	private static boolean[] isVisited = new boolean[INF];

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String num = br.readLine();
		
		System.out.println(bfs(num));
	}

	private static class NumberProcess {
		String num;
		String tmp;

		public NumberProcess(String num, String tmp) {
			this.num = num;
			this.tmp = tmp;
		}
	}

	private static String bfs(String num) {
		Queue<NumberProcess> q = new LinkedList();
		q.offer(new NumberProcess(num, EMPTY));
		
		isVisited[Integer.parseInt(num)] = true;

		while (!q.isEmpty()) {
			NumberProcess current = q.poll();
			
			int mul = Integer.parseInt(current.num) * 2;
			mul = mul >= INF ? mul % INF : mul;
			
			int div = Integer.parseInt(current.num) / 2;
			char[] tmp = current.num.toCharArray();
			Arrays.sort(tmp);

			String ascTmp = EMPTY, descTmp = EMPTY;
			for (int i = 0; i < tmp.length; i++) {
				ascTmp += tmp[i];
			}
			
			for (int i = tmp.length - 1; i >= 0; i--) {
				descTmp += tmp[i];
			}
			
			int asc = Integer.parseInt(ascTmp);
			int desc = Integer.parseInt(descTmp);

			if (!isVisited[div]) {
				isVisited[div] = true;
				
				if (div == 1) return current.tmp + DIVIDE2;
				q.offer(new NumberProcess(String.valueOf(div), current.tmp + DIVIDE2));
			}

			if (!isVisited[mul]) {
				isVisited[mul] = true;
				
				q.offer(new NumberProcess(String.valueOf(mul), current.tmp + MULTIPLY2));
			}

			if (!isVisited[asc]) {
				isVisited[asc] = true;
				
				if (asc == 1) return current.tmp + SORT;
				q.offer(new NumberProcess(String.valueOf(asc), current.tmp + SORT));
			}

			if (!isVisited[desc]) {
				isVisited[desc] = true;
				
				if (desc == 1) return current.tmp + TROS;
				q.offer(new NumberProcess(String.valueOf(desc), current.tmp + TROS));
			}
		}
		
		return NO_ANSWER;
	}
}
