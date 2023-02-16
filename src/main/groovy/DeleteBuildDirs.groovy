class DeleteBuildDirs {
    static void main(String[] args) {
        println "Hello World!"
        println("begin.....")
        deleteAllBuildDirs(new File("/Users/apus/IdeaProjects"))
        println("end.....")
    }

    //遍历所有的文件
    static void deleteAllBuildDirs(File file) {
        if (file.isDirectory()) {
            if ("build" == file.getName()) {
                println file.getAbsolutePath()
            } else {
                file.listFiles().each {f ->
                    deleteAllBuildDirs(f)
                }
            }
        }
    }

    static void deleteAllFiles(File file) {
        if (file.isDirectory()) {
            for (final def f in file.listFiles()) {
               deleteAllFiles(f)
            }
            file.deleteDir()
        } else if(file.isFile()) {
            file.delete()
        }
    }

}
