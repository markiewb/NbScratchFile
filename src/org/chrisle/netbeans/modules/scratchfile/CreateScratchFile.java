package org.chrisle.netbeans.modules.scratchfile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.cookies.OpenCookie;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileSystem;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle.Messages;

@ActionID(
    category = "Tools",
    id = "org.chrisle.netbeans.modules.scratchfile.CreateScratchFile"
)
@ActionRegistration(
    displayName = "#CTL_CreateScratchFile",
    iconBase = "org/chrisle/netbeans/modules/scratchfile/resources/add_file.png"
)
@ActionReferences({
    @ActionReference(path = "Menu/File", position = 150),
    @ActionReference(path = "Shortcuts", name = "DOS-N")
})
@Messages("CTL_CreateScratchFile=New Scratch File...")
public final class CreateScratchFile implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            FileSystem fs = FileUtil.createMemoryFileSystem();
            FileObject fob = fs.getRoot().createData("Untitled");
            DataObject data = DataObject.find(fob);
            OpenCookie cookie = (OpenCookie)data.getLookup().lookup(OpenCookie.class);
            cookie.open();
        } catch (DataObjectNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }
}