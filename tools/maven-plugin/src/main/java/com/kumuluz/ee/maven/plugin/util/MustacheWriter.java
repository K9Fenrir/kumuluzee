/*
 *  Copyright (c) 2014-2017 Kumuluz and/or its affiliates
 *  and other contributors as indicated by the @author tags and
 *  the contributor list.
 *
 *  Licensed under the MIT License (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  https://opensource.org/licenses/MIT
 *
 *  The software is provided "AS IS", WITHOUT WARRANTY OF ANY KIND, express or
 *  implied, including but not limited to the warranties of merchantability,
 *  fitness for a particular purpose and noninfringement. in no event shall the
 *  authors or copyright holders be liable for any claim, damages or other
 *  liability, whether in an action of contract, tort or otherwise, arising from,
 *  out of or in connection with the software or the use or other dealings in the
 *  software. See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.kumuluz.ee.maven.plugin.util;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.logging.Logger;

public class MustacheWriter {

    private static MustacheFactory mf = new DefaultMustacheFactory();

    private static Logger log = Logger.getLogger(MustacheWriter.class.getName());

    /**
     * Writes a file in the output directory with the given template and data
     *
     * @param templateFile name of template file to use (located in the resources directory)
     * @param finalName final name of written file (will be created in the target directory)
     * @param kumuluzProject an object of data for the mustache compiler to use in the templates
     * @param outputDirectory file output directory
     */
    public static void writeFileFromTemplate(String templateFile, String finalName, KumuluzProject kumuluzProject, File outputDirectory){

        log.info("Writing file: " + finalName);

        Mustache m = mf.compile(templateFile);

        StringWriter writer = new StringWriter();

        try {
            m.execute(writer, kumuluzProject).flush();
            FileWriter fileWriter = new FileWriter(outputDirectory.getAbsolutePath() + File.separator + finalName);

            fileWriter.write(writer.toString());
            fileWriter.close();
        }
        catch (IOException e){
            e.printStackTrace();
            log.warning("Error writing file: " + finalName);
        }
    }

}
