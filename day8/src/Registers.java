import java.util.Collections;
import java.util.HashMap;

/**
 * Created by Mark on 8-12-2017.
 */
public class Registers {
    HashMap<String, Integer> registers = new HashMap<>();
    public static int WORD_TARGET_REGISTER = 0;
    public static int WORD_INC_OR_DEC = 1;
    public static int WORD_INC_AMOUNT = 2;
    public static int WORD_CONDITION_REGISTER = 4;
    public static int WORD_CONDITION_OPERATOR = 5;
    public static int WORD_CONDITION_VALUE = 6;

    public int max = 0;

    public void initRegister(String register) {
        if(!registers.containsKey(register)) {
            registers.put(register, new Integer(0));
        }
    }

    public void addRegisterValue(String register, Integer amount) {
        this.registers.put(register, this.registers.get(register) + amount);
        if(this.registers.get(register) > max) {
            max = this.registers.get(register);
        }
    }

    public void handleLine(String line) {
        String[] words = line.split("\\s+");
        this.initRegister(words[WORD_CONDITION_REGISTER]);
        this.initRegister(words[WORD_TARGET_REGISTER]);
        if(conditionTrue(words[WORD_CONDITION_REGISTER], words[WORD_CONDITION_OPERATOR], Integer.parseInt(words[WORD_CONDITION_VALUE]))) {
            String register = words[WORD_TARGET_REGISTER];
            Integer value = Integer.parseInt(words[WORD_INC_AMOUNT]);
            if (words[WORD_INC_OR_DEC].equals("dec")) {
                value = value * -1;
            }
            this.addRegisterValue(register, value);
        }
    }

    public boolean conditionTrue(String register, String comparator, Integer value) {
        boolean returnVal = false;
        switch(comparator) {
            case "==": returnVal = this.registers.get(register).equals(value); break;
            case "!=": returnVal = !this.registers.get(register).equals(value); break;
            case ">=": returnVal = this.registers.get(register) >= value; break;
            case "<=": returnVal = this.registers.get(register) <= value; break;
            case ">": returnVal = this.registers.get(register) > value; break;
            case "<": returnVal = this.registers.get(register) < value; break;
        }
        return returnVal;
    }

    public HashMap<String, Integer> getRegisters() {
        return this.registers;
    }

    public int getMaxRegister() {
        return Collections.max(this.registers.values());
    }

    public int getMaxEverRegister() {
        return this.max;
    }

    public void printAllRegister() {
        for(String key : registers.keySet()) {
            System.out.println("REGISTER " + key + " VAL " + registers.get(key));
        }
    }


}
