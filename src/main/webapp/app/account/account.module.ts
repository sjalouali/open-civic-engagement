import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OpenCivicEngagementSharedModule } from 'app/shared/shared.module';

import { PasswordStrengthBarComponent } from './password/password-strength-bar.component';
import { RegisterComponent } from './register/register.component';
import { ActivateComponent } from './activate/activate.component';
import { PasswordComponent } from './password/password.component';
import { PasswordResetInitComponent } from './password-reset/init/password-reset-init.component';
import { PasswordResetFinishComponent } from './password-reset/finish/password-reset-finish.component';
import { SettingsComponent } from './settings/settings.component';
import { accountState } from './account.route';
import { ActionServiceOceUpdateComponent } from './register-action-service-oce/register-action-service-oce-update.component';
import { PropositionServiceOceUpdateComponent } from './register-proposition-service-oce/register-proposition-service-oce-update.component';
import { PlacesComponent } from './places.component';

@NgModule({
  imports: [OpenCivicEngagementSharedModule, RouterModule.forChild(accountState)],
  declarations: [
    ActivateComponent,
    RegisterComponent,
    PasswordComponent,
    PasswordStrengthBarComponent,
    PasswordResetInitComponent,
    PasswordResetFinishComponent,
    SettingsComponent,
    ActionServiceOceUpdateComponent,
    PropositionServiceOceUpdateComponent,
    PlacesComponent
  ]
})
export class AccountModule {}
