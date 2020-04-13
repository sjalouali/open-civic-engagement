import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OpenCivicEngagementSharedModule } from 'app/shared/shared.module';
import { CommentOceOceComponent } from './comment-oce-oce.component';
import { CommentOceOceDetailComponent } from './comment-oce-oce-detail.component';
import { CommentOceOceUpdateComponent } from './comment-oce-oce-update.component';
import { CommentOceOceDeleteDialogComponent } from './comment-oce-oce-delete-dialog.component';
import { commentOceRoute } from './comment-oce-oce.route';

@NgModule({
  imports: [OpenCivicEngagementSharedModule, RouterModule.forChild(commentOceRoute)],
  declarations: [CommentOceOceComponent, CommentOceOceDetailComponent, CommentOceOceUpdateComponent, CommentOceOceDeleteDialogComponent],
  entryComponents: [CommentOceOceDeleteDialogComponent]
})
export class OpenCivicEngagementCommentOceOceModule {}
