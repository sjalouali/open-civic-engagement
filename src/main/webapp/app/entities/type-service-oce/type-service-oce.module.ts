import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSharedModule } from 'app/shared/shared.module';
import { TypeServiceOceComponent } from './type-service-oce.component';
import { TypeServiceOceDetailComponent } from './type-service-oce-detail.component';
import { TypeServiceOceUpdateComponent } from './type-service-oce-update.component';
import { TypeServiceOceDeleteDialogComponent } from './type-service-oce-delete-dialog.component';
import { typeServiceRoute } from './type-service-oce.route';

@NgModule({
  imports: [JhipsterSharedModule, RouterModule.forChild(typeServiceRoute)],
  declarations: [
    TypeServiceOceComponent,
    TypeServiceOceDetailComponent,
    TypeServiceOceUpdateComponent,
    TypeServiceOceDeleteDialogComponent
  ],
  entryComponents: [TypeServiceOceDeleteDialogComponent]
})
export class JhipsterTypeServiceOceModule {}
