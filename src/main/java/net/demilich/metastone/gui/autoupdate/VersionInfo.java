package net.demilich.metastone.gui.autoupdate;

public class VersionInfo {

	private static final int MAJOR_INDEX = 0;
	private static final int MINOR_INDEX = 1;
	private static final int REVESION_INDEX = 2;

	public String version;
	public String[] whatsNew;

	public boolean isNewerVersionAvailable(String localVersionStr) {
		Integer[] localVersion = parseVersionString(localVersionStr);
		Integer[] remoteVersion = parseVersionString(version);
		if (remoteVersion[MAJOR_INDEX] > localVersion[MAJOR_INDEX]) {
			return true;
		}
		if (remoteVersion[MAJOR_INDEX] == localVersion[MAJOR_INDEX] && remoteVersion[MINOR_INDEX] > localVersion[MINOR_INDEX]) {
			return true;
		}
		if (remoteVersion[MAJOR_INDEX] == localVersion[MAJOR_INDEX] && remoteVersion[MINOR_INDEX] == localVersion[MINOR_INDEX]
				&& remoteVersion[REVESION_INDEX] > localVersion[REVESION_INDEX]) {
			return true;
		}
		return false;
	}

	private static Integer[] parseVersionString(String rawVersionString) {
		String versionString = rawVersionString.replaceAll("[^\\d+|^\\.]", "");
		String[] parts = versionString.split("\\.");
		Integer[] versions = new Integer[parts.length];
		for (int i = 0; i < versions.length; i++) {
			versions[i] = Integer.parseInt(parts[i]);
		}
		return versions;
	}
}
