package cookbook.cookbookrecipeapplication.models;

import java.util.List;

public class InstructionList {

    private List<Instruction> instructions;

    public InstructionList() {
    }

    public InstructionList(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<Instruction> instructions) {
        this.instructions = instructions;
    }
}
