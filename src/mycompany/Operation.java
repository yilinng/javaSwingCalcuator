package mycompany;

import java.util.Arrays;
import java.util.List;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Operation {

	private String result;
	private String sliceResult;
	private double answer;
	private int idxOperation;
	private List<String> operationList = Arrays.asList("+", "-", "*", "/");
	private String sliceStr1;
	private String sliceStr2;
	private boolean haveDot1;
	private boolean haveDot2;
	private String dot = ".";

	public Operation() {
		InitVal();
	}

	public void InitVal() {
		result = "0";
		sliceResult = "0";
		answer = 0;
		idxOperation = -1;
		sliceStr1 = "";
		sliceStr2 = "";
		haveDot1 = false;
		haveDot2 = false;
	}

	public String getResult() {
		return result;
	}

	public double getAnswer() {
		return answer;
	}

	public void setResult(String txt) {
		result += txt;
		sliceResult = result;
	}

	public void defaultNbAction() {
		initStr();
		lastIsNotNb();
	}

	public void afterNbClickAction() {
		String str = sliceDefaultNb(result);
		answer = convertToDouble(str);
	}

	// 12 * 5 + -> slice result
	public void haveMutiOperation() {
		List<String> list = sliceWithIdx(sliceResult);
		
		System.out.println("haveMutiOperation  list " + list);
		
		if (list.size() == 2) {
			splitStrToList(sliceResult);
			sliceResult = convertToString(answer);
			result = sliceResult;
			System.out.println("haveMutiOperation  sliceResult " + sliceResult);
		}
		
	}

	public void backAction() {
		idxOperation = checkOperationIdx(sliceResult);
		List<String> list = sliceWithIdx(sliceResult);
		String str = "0";

		System.out.println("backAction() idxOperation " + idxOperation);
		System.out.println("backAction() list " + list);
		// [1-9] -> 0
		if (list.size() == 1) {
			str = conditionList(list, 0);
			result = str;
			sliceResult = result;
		} else {
			if (idxOperation != -1) {
				str = conditionList(list, 1);
				System.out.println("idxOperation != -1 backAction conditionList result " + str);
				result = result.substring(0, idxOperation + 2) + str;
				sliceResult = result;
			}
		}

		System.out.println(" backAction conditionList result " + str);
		answer = convertToDouble(str);
	}

	private String conditionList(List<String> list, int idx) {
		String str = list.get(idx);
		String strRemoveSpace = str.replaceAll("\\s+", "");

		System.out.println(" conditionList  strRemoveSpace " + strRemoveSpace);

		if (strRemoveSpace.length() == 1) {
			if (!list.get(idx).equals("0"))
				str = "0";
		} else {
			str = list.get(idx).substring(0, list.get(idx).length() - 1);
		}

		System.out.println(" conditionList result" + str);

		return str;
	}

	private List<String> sliceWithIdx(String str) {
		idxOperation = checkOperationIdx(str);
		return sliceStr(idxOperation);
	}

	public String sliceDefaultNb(String txt) {
		List<String> list = sliceWithIdx(txt);
		String str = "";
		System.out.println("sliceDefaultNb idxOperation" + idxOperation);
		if (list.size() == 1) {
			System.out.println("sliceDefaultNb list == 1  " + list.get(0));
			str = list.get(0);
		} else if (list.size() == 2) {
			System.out.println("sliceDefaultNb list == 2  " + list.get(1));
			str = list.get(1);
		}

		return str;
	}

	public void nb0Action() {
		lastIsNotNb();
		List<String> list = sliceWithIdx(sliceResult);
		if (list.size() == 1) {
			// check sliceStr1[0] is zero
			System.out.println("firstIsZero(list.get(0)) " + firstIsZero(list.get(0)));

			if (!firstIsZero(list.get(0))) {
				result += 0;
				sliceResult = result;
				System.out.println("sliceStr2 empty, !FirstIsZero(slicestr1) " + list.get(0));

			}
		} else {
			// check sliceStr2[0] is zero -> 17 + [1_9]
			if (!firstIsZero(list.get(1)) && !list.get(1).equals(" ")) {
				result += 0;
				sliceResult = result;
				System.out.println("sliceStr2 not empty, !FirstIsZero(slicestr2) " + list.get(1));

			}
			// 17 +
			if (list.get(1).equals(" ")) {
				result += 0;
				sliceResult = result;
				System.out.println("sliceStr2 not empty, (list.get(1).equals space " + list.get(1));
			}
		}
	}

	public void dotClickAction() {
		// check before operation have dot.
		// slice string with operation then check dot.
		List<String> list = sliceWithIdx(sliceResult);

		if (list.size() == 2) {

			System.out.println(" check list[1] is empty. " + list);
			// add space and zero
			if (list.get(1).isEmpty()) {
				System.out.println("list[1] is empty.");
				result += " 0";
				sliceResult = result;
			}

			haveDot2 = list.get(1).contains(dot);
			System.out.println("idxOperation != -1  sliceStr2 " + list.get(1) + "havedot " + haveDot2);

			addDot(haveDot2);
		} else {

			haveDot1 = list.get(0).contains(dot);
			System.out.println("idxOperation == -1  sliceStr0" + list.get(0) + "havedot " + haveDot1);
			addDot(haveDot1);
		}
	}

	public void totalClickAction() {
		splitStrToList(sliceResult);
		idxOperation = -1;
		sliceResult = "0";
		result = decimalValIs0();
	}

	private boolean checkHaveDot() {
		return convertToString(answer).contains(dot);
	}

	private String sliceWithDot(String txt) {
		String regex = "[,\\.\\s]";
		String[] arr = txt.split(regex);
		String str = "";

		if (arr[1].equals("0")) {
			str = arr[0];
		} else {
			str = txt;
		}

		System.out.println("sliceWithDot result " + str);

		return str;
	}

	// https://stackoverflow.com/questions/67305423/how-to-check-if-a-number-has-a-decimal-place-is-a-whole-number
	private String decimalValIs0() {
		String str = "";
		if (checkHaveDot()) {
			str = sliceWithDot(convertToString(answer));
		} else {
			str = convertToString(answer);
		}

		return str;
	}

	// https://stackoverflow.com/questions/65293033/why-bigdecimal-scale-not-working-in-java
	static BigDecimal roundNumber(double num, int scaleRound1, int scaleRound2) {
		return new BigDecimal(num).setScale(scaleRound1, RoundingMode.HALF_UP).setScale(scaleRound2,
				RoundingMode.HALF_UP);
	}

	public double doOperation(double num1, double num2, String op) {
		double result = 0; // Default value is "not-a-number" if an operation, such as division, could
							// result in an error.
		// Use a switch statement to do the math.
		switch (op) {
		case "+":
			result = num1 + num2;
			break;
		case "-":
			result = num1 - num2;
			break;
		case "*":
			result = num1 * num2;
			break;
		case "/":
			// Ask the user to enter a non-zero divisor.
			if (num2 != 0) {
				result = num1 / num2;
			}
			break;
		// Return text for an incorrect option entry.
		default:
			break;
		}
		return result;
	}

	private void initStr() {
		// clean default 0 when input 1-9
		if (firstIsZero(sliceResult) && sliceResult.length() == 1) {
			result = "";
			sliceResult = result;
		}
	}

	private void lastIsNotNb() {
		if (CheckLastIsOperation()) {
			result += " ";
			sliceResult = result;
		}
	}

	private int returnLastLength() {
		return sliceResult.length() - 1;
	}

	// https://stackoverflow.com/questions/8172420/how-to-convert-a-char-to-a-string
	private boolean CheckLastIsOperation() {
		int index = returnLastLength();
		if (index > -1) {
			char s = sliceResult.charAt(index);
			// convert char to string
			boolean v = operationList.contains(s + "");
			if (v) {
				return true;
			}

		}
		return false;
	}

	private boolean lastIsDot() {
		int index = returnLastLength();
		if (index > -1) {
			char s = sliceResult.charAt(index);
			boolean v = String.valueOf(s).equals(dot);
			if (v) {
				return true;
			}
		}
		return false;
	}

	public void concatSliceStrWithOperation(String operation) {
		int index = returnLastLength();
		// 8. -> 8
		if (lastIsDot()) {
			result = result.substring(0, index);
		}

		if (CheckLastIsOperation()) {
			String substr = result.substring(0, index);
			result = substr + " " + operation;
		} else {
			result = result + " " + operation;
		}
		
		sliceResult = result;
	}

	private boolean firstIsZero(String str) {
		return str.charAt(0) == '0';
	}

	// https://stackoverflow.com/questions/5769669/convert-string-to-double-in-java
	public double convertToDouble(String txt) {
		return Double.parseDouble(txt);
	}

	public String convertToString(double nb) {
		return String.valueOf(nb);
	}

	private void splitStrToList(String str) {

		// check last is dot

		String[] arr = str.split(" ");

		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}

		// ex: 3 + 2 = 5
		if (arr.length > 2) {
			answer = doOperation(convertToDouble(arr[0]), convertToDouble(arr[2]), arr[1]);
		}

		// ex: 3+= -> 6, 3*= -> 9, 3-= -> 0, 3/= -> 1
		if (arr.length == 2 && operationList.contains(arr[1])) {
			answer = doOperation(convertToDouble(arr[0]), convertToDouble(arr[0]), arr[1]);
		}

		System.out.println("work...done..answer" + answer);
	}

	private int checkOperationIdx(String str) {

		for (String item : operationList) {
			if (str.contains(item)) {
				// find item index in operationStr
				idxOperation = str.indexOf(item);
			}
		}
		return idxOperation;
	}

	private List<String> sliceStr(int nb) {
		List<String> list = new ArrayList<String>();

		if (nb != -1 && idxOperation != -1) {
			sliceStr1 = sliceResult.substring(0, idxOperation);
			sliceStr2 = sliceResult.substring(idxOperation + 1);

			list.add(sliceStr1);
			list.add(sliceStr2);
		} else {
			sliceStr1 = sliceResult.substring(0);

			list.add(sliceStr1);
		}

		return list;
	}

	private void addDot(boolean str) {
		if (!str) {
			result += ".";
			sliceResult = result;
			System.out.println("addDot false ${result}");

		}
	}

}
