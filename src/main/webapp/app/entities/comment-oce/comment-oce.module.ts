import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OpenCivicEngagementSharedModule } from 'app/shared/shared.module';
import { CommentOceComponent } from './comment-oce.component';
import { CommentOceDetailComponent } from './comment-oce-detail.component';
import { CommentOceUpdateComponent } from './comment-oce-update.component';
import { CommentOceDeleteDialogComponent } from './comment-oce-delete-dialog.component';
import { commentRoute } from './comment-oce.route';

@NgModule({
  imports: [OpenCivicEngagementSharedModule, RouterModule.forChild(commentRoute)],
  declarations: [CommentOceComponent, CommentOceDetailComponent, CommentOceUpdateComponent, CommentOceDeleteDialogComponent],
  entryComponents: [CommentOceDeleteDialogComponent]
})
export class OpenCivicEngagementCommentOceModule {}
