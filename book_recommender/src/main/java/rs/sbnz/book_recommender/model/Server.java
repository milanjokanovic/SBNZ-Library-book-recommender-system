package rs.sbnz.book_recommender.model;

public class Server {
    public String name;
    public int processors;
    public int memory;
    public int diskspace;
    public boolean isValid=true;

    public Server(String name, int processors, int memory, int diskspace) {
        this.name = name;
        this.processors = processors;
        this.memory = memory;
        this.diskspace = diskspace;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProcessors() {
        return processors;
    }

    public void setProcessors(int processors) {
        this.processors = processors;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public int getDiskspace() {
        return diskspace;
    }

    public void setDiskspace(int diskspace) {
        this.diskspace = diskspace;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }
}
