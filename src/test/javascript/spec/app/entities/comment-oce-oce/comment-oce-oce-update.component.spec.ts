import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { OpenCivicEngagementTestModule } from '../../../test.module';
import { CommentOceOceUpdateComponent } from 'app/entities/comment-oce-oce/comment-oce-oce-update.component';
import { CommentOceOceService } from 'app/entities/comment-oce-oce/comment-oce-oce.service';
import { CommentOceOce } from 'app/shared/model/comment-oce-oce.model';

describe('Component Tests', () => {
  describe('CommentOceOce Management Update Component', () => {
    let comp: CommentOceOceUpdateComponent;
    let fixture: ComponentFixture<CommentOceOceUpdateComponent>;
    let service: CommentOceOceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [OpenCivicEngagementTestModule],
        declarations: [CommentOceOceUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(CommentOceOceUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CommentOceOceUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CommentOceOceService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CommentOceOce(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new CommentOceOce();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
