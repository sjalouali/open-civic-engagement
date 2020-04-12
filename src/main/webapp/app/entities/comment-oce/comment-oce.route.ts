import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICommentOce, CommentOce } from 'app/shared/model/comment-oce.model';
import { CommentOceService } from './comment-oce.service';
import { CommentOceComponent } from './comment-oce.component';
import { CommentOceDetailComponent } from './comment-oce-detail.component';
import { CommentOceUpdateComponent } from './comment-oce-update.component';

@Injectable({ providedIn: 'root' })
export class CommentOceResolve implements Resolve<ICommentOce> {
  constructor(private service: CommentOceService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICommentOce> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((comment: HttpResponse<CommentOce>) => {
          if (comment.body) {
            return of(comment.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CommentOce());
  }
}

export const commentRoute: Routes = [
  {
    path: '',
    component: CommentOceComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterApp.comment.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CommentOceDetailComponent,
    resolve: {
      comment: CommentOceResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterApp.comment.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CommentOceUpdateComponent,
    resolve: {
      comment: CommentOceResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterApp.comment.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CommentOceUpdateComponent,
    resolve: {
      comment: CommentOceResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterApp.comment.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
