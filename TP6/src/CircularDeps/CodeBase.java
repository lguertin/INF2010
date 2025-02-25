package CircularDeps;

import java.util.HashSet;

public class CodeBase {
    private SourceFile[] sourceFiles;

    public CodeBase(SourceFile[] sourceFiles)
    {
        this.sourceFiles = sourceFiles;
    }

    public boolean hasCircularDependencies()
    {
        for (SourceFile file : this.sourceFiles) {
            HashSet<SourceFile> dependentFiles = new HashSet<>();
            if (existsCircularDependencies(file, dependentFiles)) {
                return true;
            }
        }
        return false;
    }

    // DFS partant de <<file>> détectant s'il existe
    // des dépendances circulaires dans les fichiers source.
    private boolean existsCircularDependencies(SourceFile file, HashSet<SourceFile> dependentFiles)
    {
    	dependentFiles.add(file);
    	for(SourceFile files : file.getDependencies()) {
    		if (!dependentFiles.contains(files)) {
    			if(existsCircularDependencies(files, dependentFiles))
    				return true;
    		}
    		else
    			return true;
    	}
    	return false;
    }
}
