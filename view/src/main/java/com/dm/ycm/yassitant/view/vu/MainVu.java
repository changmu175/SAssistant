package com.dm.ycm.yassitant.view.vu;

import com.dm.ycm.yassitant.command.MainCommand;
import com.dm.ycm.yassitant.mvp.view.vu.ActivityVu;

/**
 * Created by ycm on 2017/6/27.
 * Description:
 * Modified by:
 */

public interface MainVu extends ActivityVu<MainCommand>{
    boolean closeDrawer();
}
