
/**
 * Created by ruth on 2016/09/20.
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;

class SortVer2 {
	private static TestMeasurement testMeasurement = new TestMeasurement();

	public static void main(String[] args) {
		testMeasurement.start();
		// 0<n&&n<=1000000000を格納
		ArrayList<Integer> array = new ArrayList<Integer>();
		// 10000000000<n && n<=20000000000を格納
		ArrayList<Integer> list = new ArrayList<Integer>();
		testMeasurement.lap("ファイル読み込み開始");
		try {
			// 読み込み処理の準備
			// System.out.println("ファイル読み込み開始");
			File file = new File("/Users/ruth/Desktop/example.txt");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String s;
			testMeasurement.lap("ファイル01書き出し開始");
			while ((s = br.readLine()) != null) {
				int n = 0;
				if (s.length() > 0) {
					n = Integer.parseInt(s);
				}
				// testMeasurement.lap("ファイル01書き出し開始");
				if (1000000000 >= n) {
					array.add(n);
					quick_sort(array);
					try {
						// 書き出し処理の準備
						FileOutputStream fos = new FileOutputStream("/Users/ruth/Desktop/01.txt");
						OutputStreamWriter osw = new OutputStreamWriter(fos);
						BufferedWriter bw = new BufferedWriter(osw);
						// System.out.println("01ファイル書き出し開始");
						Iterator<Integer> it = array.iterator();
						while (it.hasNext()) {
							Integer e;
							e = it.next();
							bw.write(e + "" + System.getProperty("line.separator"));
						}
						bw.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
					testMeasurement.lap("ファイル01書き出し終了");
					// System.out.println("01ファイル書き込み完了");
					// array.clear();
				} else if (1000000000 < n && n <= 2000000000) {
					list.add(n);
					// quick_sort(array);
					testMeasurement.lap("ファイル02書き出し開始");
					try {
						// 書き出し処理の準備
						FileOutputStream fos = new FileOutputStream("/Users/ruth/Desktop/02.txt");
						OutputStreamWriter osw = new OutputStreamWriter(fos);
						BufferedWriter bw = new BufferedWriter(osw);
						System.out.println("02ファイル書き出し");
						Iterator<Integer> it = array.iterator();
						while (it.hasNext()) {
							Integer e;
							e = it.next();
							bw.write(e + "" + System.getProperty("line.separator"));
						}
						bw.flush();
						// list.clear();
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println("02ファイル書き込み完了");
					testMeasurement.lap("ファイル02書き出し終了");
				}
			}
			testMeasurement.lap("ファイル読み込み終了");
			br.close();
			// System.gc();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("書き込み完了");
		array.clear();
		list.clear();
		System.gc();
		// 02を読み込む
		try {
			System.out.println("02.txtファイルを読み込み開始");
			File file_2 = new File("/Users/ruth/Desktop/02.txt");
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file_2));
			String ch;
			while ((ch = bufferedReader.readLine()) != null) {
				int m = 0;
				if (ch.length() > 0) {
					m = Integer.parseInt(ch);
				}
				// listに格納してソート
				list.add(m);
				quick_sort(list);
				try {
					File file03 = new File("/Users/ruth/Desktop/01.txt");
					FileOutputStream fos = new FileOutputStream(file03, true);
					OutputStreamWriter osw = new OutputStreamWriter(fos);
					BufferedWriter bw = new BufferedWriter(osw);
					Iterator<Integer> it = array.iterator();
					while (it.hasNext()) {
						Integer e;
						e = it.next();
						bw.write(e + "" + System.getProperty("line.separator"));
					}
					bw.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				// System.out.println("書き込み完了");
				// array.clear();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		testMeasurement.end();
		System.out.println("書き込み完了");
		list.clear();
	}

	static void _quick_sort(ArrayList<Integer> d, int left, int right) {
		if (left >= right) {
			return;
		}
		// pに中央値を入れている
		int midium_position = (left + right) / 2;
		int p = d.get(midium_position);
		int l = left;
		int r = right;
		int tmp;
		// lがr同じかそれ以下の時
		while (l <= r) {
			// lとpを比較した時にlが中央値よりも小さい時は交換から外す
			// 添字lがpよりも大きい値に定まるまでループを続ける
			while (d.get(l) < p) {
				l++;
			}
			// rとpを比較した時にlが中央値よりも大きい時は交換から外す
			// 添字rがpよりも大きい値に定まるまでループを続ける
			while (d.get(r) > p) {
				r--;
			}
			if (l <= r) {
				//
				tmp = d.get(l);
				d.set(l, d.get(r));
				d.set(r, tmp);
				l++;
				r--;
			}
		}
		_quick_sort(d, left, r);// ピボットより左側をクイックソート
		_quick_sort(d, l, right); // ピボットより右側をクイックソート
	}

	static void quick_sort(ArrayList d) {
		// 概要 ：クイックソートする関数
		// 引数d：昇順になってない数列
		_quick_sort(d, 0, d.size() - 1); // ピボットより右側をクイックソート
	}

	private static class TestMeasurement {
		private Long startTime;
		private Long lastTime;
		private Long endTime;

		public void start() {
			startTime = lastTime = System.currentTimeMillis();
			System.out.println("★★★★計測スタート★★★★");
		}

		public void lap(String keyword) {
			long lastTime = System.currentTimeMillis();
			System.out.println("★★★★途中経過" + keyword == null ? "" : keyword + ":" + (lastTime - this.lastTime) + " ms");
			this.lastTime = lastTime;
		}

		public void end() {
			endTime = System.currentTimeMillis();
			System.out.println("★★★★計測終了:" + (endTime - startTime) + " ms");
		}
	}
}
