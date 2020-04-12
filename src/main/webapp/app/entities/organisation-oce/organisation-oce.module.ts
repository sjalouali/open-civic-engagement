import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSharedModule } from 'app/shared/shared.module';
import { OrganisationOceComponent } from './organisation-oce.component';
import { OrganisationOceDetailComponent } from './organisation-oce-detail.component';
import { OrganisationOceUpdateComponent } from './organisation-oce-update.component';
import { OrganisationOceDeleteDialogComponent } from './organisation-oce-delete-dialog.component';
import { organisationRoute } from './organisation-oce.route';

@NgModule({
  imports: [JhipsterSharedModule, RouterModule.forChild(organisationRoute)],
  declarations: [
    OrganisationOceComponent,
    OrganisationOceDetailComponent,
    OrganisationOceUpdateComponent,
    OrganisationOceDeleteDialogComponent
  ],
  entryComponents: [OrganisationOceDeleteDialogComponent]
})
export class JhipsterOrganisationOceModule {}
