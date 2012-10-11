package extras;

/*
 package extras;
 * Mitchell Ludwig
 * #10015370
 * BMP file writer and bitmap data container
 */

import java.io.*;

public class Bitmap {
	public static final int	COLOR_BLACK			= 0x000000;
	public static final int	COLOR_DARK_GREY		= 0x444444;
	public static final int	COLOR_LIGHT_GREY	= 0x999999;
	public static final int	COLOR_WHITE			= 0xFFFFFF;
	
	public static final int	COLOR_RED			= 0xFF0000;
	public static final int	COLOR_GREEN			= 0x00FF00;
	public static final int	COLOR_BLUE			= 0x0000FF;
	public static final int	COLOR_YELLOW		= 0xFFFF00;
	public static final int	COLOR_CYAN			= 0x00FFFF;
	public static final int	COLOR_MAGENTA		= 0xFF00FF;
	
	private final int[][]	_iaPixels;						// Grid of pixels
	private final int		_iWidth;						// Width of bitmap
	private final int		_iHeight;						// Height of bitmap
															
	// Constructors
	public Bitmap() {
		_iaPixels = null;
		_iWidth = 0;
		_iHeight = 0;
	}
	
	public Bitmap(final int iWidth, final int iHeight) {
		_iaPixels = new int[iWidth][iHeight];
		_iWidth = iWidth;
		_iHeight = iHeight;
	}
	
	// Get the color value for a single pixel
	public int GetPixel(final int iX, final int iY) {
		if (iX >= 0 && iX < _iWidth && iY >= 0 && iY < _iHeight) {
			return _iaPixels[iX][iY];
		}
		return -1;
	}
	
	// Set the color of a single pixel
	public void SetPixel(final int iX, final int iY, final int iColor) {
		if (iX >= 0 && iX < _iWidth && iY >= 0 && iY < _iHeight) {
			_iaPixels[iX][iY] = iColor;
		}
	}
	
	// Draw a rectangle border
	public void DrawRect(int iX1, int iY1, int iX2, int iY2, final int iBorderColor) {
		if (iX1 > iX2) {
			final int iSwap = iX1;
			iX1 = iX2;
			iX2 = iSwap;
		}
		if (iY1 > iY2) {
			final int iSwap = iY1;
			iY1 = iY2;
			iY2 = iSwap;
		}
		for (int x = iX1; x <= iX2; x++) {
			SetPixel(x, iY1, iBorderColor);
			SetPixel(x, iY2, iBorderColor);
		}
		for (int y = iY1 + 1; y <= iY2; y++) {
			SetPixel(iX1, y, iBorderColor);
			SetPixel(iX2, y, iBorderColor);
		}
	}
	
	// Draw a filled in rectangle
	public void DrawRect(int iX1, int iY1, int iX2, int iY2, final int iBorderColor, final int iFillColor) {
		if (iX1 > iX2) {
			final int iSwap = iX1;
			iX1 = iX2;
			iX2 = iSwap;
		}
		if (iY1 > iY2) {
			final int iSwap = iY1;
			iY1 = iY2;
			iY2 = iSwap;
		}
		for (int x = iX1 + 1; x <= iX2 - 1; x++) {
			for (int y = iY1 + 1; y <= iY2 - 1; y++) {
				SetPixel(x, y, iFillColor);
			}
		}
		DrawRect(iX1, iY1, iX2, iY2, iBorderColor);
	}
	
	// Draw a line
	public void DrawLine(final int iX1, final int iY1, final int iX2, final int iY2, final int iColor) {
		// Width/Height
		final int iWidth = iX2 - iX1;
		final int iHeight = iY2 - iY1;
		// Absolute values of width/height
		final int iAbsWidth = Math.abs(iWidth);
		final int iAbsHeight = Math.abs(iHeight);
		
		// Directions for X and Y
		int iXInc;
		int iYInc;
		// Length of line, in pixels
		final int iLen = Math.max(iAbsWidth, iAbsHeight);
		
		// Determine X direction
		if (iWidth > 0) {
			iXInc = 1;
		} else if (iWidth < 0) {
			iXInc = -1;
		} else {
			iXInc = 0;
		}
		
		// Determine Y direction
		if (iHeight > 0) {
			iYInc = 1;
		} else if (iHeight < 0) {
			iYInc = -1;
		} else {
			iYInc = 0;
		}
		
		// Vertical line
		if (iAbsWidth == 0) {
			for (int i = 0; i <= iLen; i++) {
				SetPixel(iX1, iY1 + i * iYInc, iColor);
			}
			// Horizontal line
		} else if (iAbsHeight == 0) {
			for (int i = 0; i <= iLen; i++) {
				SetPixel(iX1 + i * iXInc, iY1, iColor);
			}
			// Diagonal line
		} else if (iAbsWidth == iAbsHeight) {
			for (int i = 0; i <= iLen; i++) {
				SetPixel(iX1 + i * iXInc, iY1 + i * iYInc, iColor);
			}
			// Tall line
		} else if (iAbsWidth < iAbsHeight) {
			for (int i = 0; i <= iLen; i++) {
				SetPixel(iX1 + i * iXInc / (iAbsHeight / iAbsWidth), iY1 + i * iYInc, iColor);
			}
			// Wide line
		} else {
			for (int i = 0; i <= iLen; i++) {
				SetPixel(iX1 + i * iXInc, iY1 + i * iYInc / (iAbsWidth / iAbsHeight), iColor);
			}
		}
	}
	
	// Write bitmap data to the file specified in sPath
	public void WriteImage(final String sPath) {
		// Open the file
		final FileOutputStream fos = OpenFileOutStream(sPath);
		try {
			// Calculate the image data length
			final int iDataLen = Align4(_iWidth * 3) * _iHeight;
			// Calculate the total file length, data+header
			final int iFileLen = iDataLen + 0x36;
			// Write the header
			final byte[] caBM = new byte[2];
			caBM[0] = 0x42; // 'B'
			caBM[1] = 0x4D; // 'M'
			fos.write(caBM); // BM
			fos.write(BigEndianBytes(iFileLen)); // File length
			fos.write(BigEndianBytes(0)); // Reserved = 0
			fos.write(BigEndianBytes(0x36)); // Data offset
			fos.write(BigEndianBytes(0x28)); // Header size
			fos.write(BigEndianBytes(_iWidth), 0, 4); // Width
			fos.write(BigEndianBytes(_iHeight), 0, 4); // Height
			fos.write(BigEndianBytes(0x00180001), 0, 4); // Reserved = 1 and
															// bit-depth = 0x18
															// = 24
			fos.write(BigEndianBytes(0), 0, 4); // Compression = none = 0
			fos.write(BigEndianBytes(iDataLen), 0, 4); // Data length
			fos.write(BigEndianBytes(0), 0, 4); // X-resolution
			fos.write(BigEndianBytes(0), 0, 4); // Y-resolution
			fos.write(BigEndianBytes(0), 0, 4); // # of colors in palette = 0
												// (non-indexed)
			fos.write(BigEndianBytes(0), 0, 4); // # of important indexed colors
												// = 0 (non-indexed)
			// Write the image data
			WritePixels(fos);
			
		} catch (final IOException e) {
			System.out.println("File I/O error");
			System.exit(1);
		}
		// Close the file
		CloseFileOutStream(fos);
	}
	
	// Convert an integer into an array of bytes stored in big endian fashion
	public byte[] BigEndianBytes(final int i) {
		final byte[] ba = new byte[4];
		ba[0] = (byte) (i & 0xFF);
		ba[1] = (byte) ((i & 0xFF00) / 0x100);
		ba[2] = (byte) ((i & 0xFF0000) / 0x10000);
		ba[3] = (byte) ((i & 0xFF000000) / 0x1000000);
		return ba;
		
	}
	
	// Opens a data stream for writing
	public static FileOutputStream OpenFileOutStream(final String sPath) {
		try {
			final FileOutputStream fos = new FileOutputStream(sPath);
			return fos;
		} catch (final IOException e) {
			System.out.println("File I/O error");
			System.exit(1);
		}
		return null;
	}
	
	// Writes the image data to an open data stream
	public void WritePixels(final FileOutputStream fos) {
		final int iLineByteWidth = Align4(_iWidth * 3); // Length of a line of
														// the image in bytes
		final byte[] baImgData = new byte[iLineByteWidth * _iHeight]; // Image
																		// data
		byte[] baPixel = null; // Array of bytes to store pixel data
		final int iPadBytes = iLineByteWidth - _iWidth * 3; // Calculate pad
															// bytes needed to
															// align each row to
															// 4
		try {
			// Loop through every pixel, left to right, bottom to top
			for (int y = 1; y <= _iHeight; y++) {
				// Write pixel data
				for (int x = 0; x < _iWidth; x++) {
					baPixel = BigEndianBytes(GetPixel(x, y));
					baImgData[(_iHeight - y) * iLineByteWidth + x * 3] = baPixel[0];
					baImgData[(_iHeight - y) * iLineByteWidth + x * 3 + 1] = baPixel[1];
					baImgData[(_iHeight - y) * iLineByteWidth + x * 3 + 2] = baPixel[2];
				}
				// Pad the line to align it to 4
				if (iPadBytes != 0) {
					for (int i = 0; i < iPadBytes; i++) {
						baImgData[(_iHeight - y + 1) * iLineByteWidth - 1 - i] = 0;
					}
				}
			}
			// Write the data
			fos.write(baImgData);
			fos.flush();
		} catch (final IOException e) {
			System.out.println("File I/O error");
			System.exit(1);
		}
	}
	
	// Close an open filestream
	public static void CloseFileOutStream(final FileOutputStream fos) {
		try {
			fos.flush();
			fos.close();
		} catch (final IOException e) {
			// If something went wrong, die.
			System.out.println("File I/O error");
			System.exit(1);
		}
	}
	
	// Align an integer to 4.
	// ex. Align4(1)=4, Align4(2)=4, Align4(3)=4, Align4(4)=4, Align4(5)=8
	public static int Align4(final int i) {
		return -(-i & -4);
	}
}
