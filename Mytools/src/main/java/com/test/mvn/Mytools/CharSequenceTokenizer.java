package com.test.mvn.Mytools;

public class CharSequenceTokenizer extends AbstractTokenizer {
	char[] buffer; // a copy of the characters in the sequence

	/**
	 * Construct a new CharSequenceTokenizer to tokenize <tt>sequence</tt>. This
	 * constructor makes an internal copy of the characters in the specified
	 * sequence.
	 * 
	 * @param sequence
	 *            the character sequence to be tokenized.
	 */
	public CharSequenceTokenizer(CharSequence sequence) {
		buffer = sequence.toString().toCharArray();
	}

	/**
	 * Set the inherited {@link #text} and {@link #numChars} fields. This class
	 * knows the complete length of the input text, so it ignores the
	 * <tt>bufferSize</tt> argument and uses the complete input sequence.
	 * 
	 * @param bufferSize
	 *            ignored in this implementation
	 */
	protected void createBuffer(int bufferSize) {
		assert text == null; // verify that we're only called once
		text = buffer;
		numChars = buffer.length;
	}

	/**
	 * Return false to indicate no more input is available.
	 * {@link #createBuffer} fills the buffer with the complete input sequence,
	 * so this method returns false to indicate that no more text is available.
	 * 
	 * @return always returns false.
	 */
	protected boolean fillBuffer() {
		return false;
	}

	public static class Test {
		public static void main(String[] args) throws java.io.IOException {
			StringBuffer text = new StringBuffer();
			for (int i = 0; i < args.length; i++)
				text.append(args[i] + " ");
			CharSequenceTokenizer t = new CharSequenceTokenizer(text.toString());
			t.tokenizeWords(true).quotes("'&", "';").skipSpaces(true);
			while (t.next() != Tokenizer.EOF)
				System.out.println(t.tokenText());
		}
	}

}
