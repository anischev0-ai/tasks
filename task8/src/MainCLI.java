public class MainCLI
{

    public static class InputArgs {
        public String inputFile;
        public String outputFile;
        public boolean error = false;
    }

    public static InputArgs parseCmdArgs(String[] args) {
        InputArgs inputArgs = new InputArgs();

        if (args.length == 0) {
            inputArgs.error = true;
            return inputArgs;
        }

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-i") || args[i].equals("--input-file")) {
                if (i + 1 < args.length) {
                    inputArgs.inputFile = args[++i];
                } else {
                    inputArgs.error = true;
                }
            } else if (args[i].startsWith("--input-file=")) {
                inputArgs.inputFile = args[i].substring("--input-file=".length());
            } else if (args[i].equals("-o") || args[i].equals("--output-file")) {
                if (i + 1 < args.length) {
                    inputArgs.outputFile = args[++i];
                } else {
                    inputArgs.error = true;
                }
            } else if (args[i].startsWith("--output-file=")) {
                inputArgs.outputFile = args[i].substring("--output-file=".length());
            } else {
                if (inputArgs.inputFile == null) {
                    inputArgs.inputFile = args[i];
                } else if (inputArgs.outputFile == null) {
                    inputArgs.outputFile = args[i];
                }
            }
        }
        return inputArgs;
    }

    private static void printHelp() {
        System.err.println("Использование:");
        System.err.println("  java MainCLI <input.txt> <output.txt>");
        System.err.println("  java MainCLI -i <input.txt> -o <output.txt>");
        System.err.println("  java MainCLI --input-file <input.txt> --output-file <output.txt>");
        System.err.println("  java MainCLI --input-file=<input.txt> --output-file=<output.txt>");
    }

    public static void main(String[] args) {

        InputArgs inputArgs = parseCmdArgs(args);

        if (inputArgs.error || inputArgs.inputFile == null || inputArgs.outputFile == null) {
            printHelp();
            System.exit(1);
        }

        try {
            int[][] array = ArrayUtils.readIntArrayFromFile(inputArgs.inputFile);
            ArrayUtils.swapColumns(array);
            ArrayUtils.writeIntArrayToFile(inputArgs.outputFile, array);
            System.out.println("Готово. Результат записан в " + inputArgs.outputFile);
        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}
