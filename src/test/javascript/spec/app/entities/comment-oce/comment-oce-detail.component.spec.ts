import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { JhipsterTestModule } from '../../../test.module';
import { CommentOceDetailComponent } from 'app/entities/comment-oce/comment-oce-detail.component';
import { CommentOce } from 'app/shared/model/comment-oce.model';

describe('Component Tests', () => {
  describe('CommentOce Management Detail Component', () => {
    let comp: CommentOceDetailComponent;
    let fixture: ComponentFixture<CommentOceDetailComponent>;
    let dataUtils: JhiDataUtils;
    const route = ({ data: of({ comment: new CommentOce(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterTestModule],
        declarations: [CommentOceDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CommentOceDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CommentOceDetailComponent);
      comp = fixture.componentInstance;
      dataUtils = fixture.debugElement.injector.get(JhiDataUtils);
    });

    describe('OnInit', () => {
      it('Should load comment on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.comment).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });

    describe('byteSize', () => {
      it('Should call byteSize from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'byteSize');
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.byteSize(fakeBase64);

        // THEN
        expect(dataUtils.byteSize).toBeCalledWith(fakeBase64);
      });
    });

    describe('openFile', () => {
      it('Should call openFile from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'openFile');
        const fakeContentType = 'fake content type';
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.openFile(fakeContentType, fakeBase64);

        // THEN
        expect(dataUtils.openFile).toBeCalledWith(fakeContentType, fakeBase64);
      });
    });
  });
});
