import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSharedModule } from 'app/shared/shared.module';
import { AppFileOceComponent } from './app-file-oce.component';
import { AppFileOceDetailComponent } from './app-file-oce-detail.component';
import { AppFileOceUpdateComponent } from './app-file-oce-update.component';
import { AppFileOceDeleteDialogComponent } from './app-file-oce-delete-dialog.component';
import { appFileRoute } from './app-file-oce.route';

@NgModule({
  imports: [JhipsterSharedModule, RouterModule.forChild(appFileRoute)],
  declarations: [AppFileOceComponent, AppFileOceDetailComponent, AppFileOceUpdateComponent, AppFileOceDeleteDialogComponent],
  entryComponents: [AppFileOceDeleteDialogComponent]
})
export class JhipsterAppFileOceModule {}
