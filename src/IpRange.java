import java.awt.List;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;

public class IpRange {
	private static final long m0 = 256 * 256 * 256;
	private static final long m1 = 256 * 256;
	private static final long m2 = 256;

	private final byte mFirstIP[];
	private final byte mSecondIP[];

	public static IpRange startIpRange(String firstIP, String secondIP) {
		return new IpRange(firstIP, secondIP);
	}

	public IpRange(String mFirstIP, String mSecondIP) {
		this.mFirstIP = parseFirstMas(mFirstIP);
		this.mSecondIP = parseSecondMas(mSecondIP);
		getIpRange();
		arrayListToString();
	}

	public long inetAddrToLong(InetAddress ia) {
		byte iab[] = ia.getAddress();
		int i0 = iab[0];
		if (i0 < 0)
			i0 += 256;
		int i1 = iab[1];
		if (i1 < 0)
			i1 += 256;
		int i2 = iab[2];
		if (i2 < 0)
			i2 += 256;
		int i3 = iab[3];
		if (i3 < 0)
			i3 += 256;
		return (m0 * i0) + (m1 * i1) + (m2 * i2) + i3;
	}

	public String ipToStr(long ip) {
		long a = ip;
		long r0 = a / m0;
		a -= (r0 * m0);
		long r1 = a / m1;
		a -= (r1 * m1);
		long r2 = a / m2;
		a -= (r2 * m2);
		long r3 = a;
		String ips = r0 + "." + r1 + "." + r2 + "." + r3;
		return ips;
	}

	private byte[] parseFirstMas(String firstIp) {
		String[] ipAd = firstIp.split("\\.");
		byte[] ip1 = new byte[ipAd.length];
		if (!ipAd[3].equals("0")) {
			if (ip1.length != 4) {
				return null;
			}
			for (int i = 0; i < ip1.length; i++) {
				ip1[i] = (byte) Byte.parseByte(ipAd[i]);
				if (ip1[i] < 0 | ip1[i] > 255) {
					return null;
				}
			}
			return ip1;
		}
		return null;
	}

	private byte[] parseSecondMas(String secondIP) {
		String[] ipAd = secondIP.split("\\.");
		byte[] ip2 = new byte[ipAd.length];
		if (ip2.length != 4) {
			return null;
		}
		for (int i = 0; i < ip2.length; i++) {
			ip2[i] = (byte) Byte.parseByte(ipAd[i]);
			if (ip2[i] < 0 | ip2[i] > 255) {
				return null;
			}
		}
		return ip2;
	}

	public ArrayList<InetAddress> getIpRange() {
		ArrayList<InetAddress> array = new ArrayList<>();
		try {
			InetAddress adr = InetAddress.getByAddress(mFirstIP);
			InetAddress adr2 = InetAddress.getByAddress(mSecondIP);

			long ip1 = inetAddrToLong(adr);
			long ip2 = inetAddrToLong(adr2);

			InetAddress check = InetAddress.getByName(ipToStr(ip1));
			while (ip1 != ip2) {
				if (check.isReachable(1000)) {
					array.add(check);
					ip1++;
					return array;
				} else
					System.out.println("adress not correct");
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return array;
	}

	public String arrayListToString() {

		for (InetAddress element : getIpRange()) {
			System.out.println(element);
		}
		return Arrays.ToString(array);
	}

	@Override
	public String toString() {
		return arrayListToString();
	}

}