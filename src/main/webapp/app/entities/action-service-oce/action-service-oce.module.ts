import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OpenCivicEngagementSharedModule } from 'app/shared/shared.module';
import { ActionServiceOceComponent } from './action-service-oce.component';
import { ActionServiceOceDetailComponent } from './action-service-oce-detail.component';
import { ActionServiceOceUpdateComponent } from './action-service-oce-update.component';
import { ActionServiceOceDeleteDialogComponent } from './action-service-oce-delete-dialog.component';
import { actionServiceRoute } from './action-service-oce.route';

@NgModule({
  imports: [OpenCivicEngagementSharedModule, RouterModule.forChild(actionServiceRoute)],
  declarations: [
    ActionServiceOceComponent,
    ActionServiceOceDetailComponent,
    ActionServiceOceUpdateComponent,
    ActionServiceOceDeleteDialogComponent
  ],
  entryComponents: [ActionServiceOceDeleteDialogComponent]
})
export class OpenCivicEngagementActionServiceOceModule {}
