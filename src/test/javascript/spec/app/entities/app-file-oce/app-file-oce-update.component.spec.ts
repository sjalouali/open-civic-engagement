import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { OpenCivicEngagementTestModule } from '../../../test.module';
import { AppFileOceUpdateComponent } from 'app/entities/app-file-oce/app-file-oce-update.component';
import { AppFileOceService } from 'app/entities/app-file-oce/app-file-oce.service';
import { AppFileOce } from 'app/shared/model/app-file-oce.model';

describe('Component Tests', () => {
  describe('AppFileOce Management Update Component', () => {
    let comp: AppFileOceUpdateComponent;
    let fixture: ComponentFixture<AppFileOceUpdateComponent>;
    let service: AppFileOceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [OpenCivicEngagementTestModule],
        declarations: [AppFileOceUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(AppFileOceUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AppFileOceUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AppFileOceService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new AppFileOce(123);
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
        const entity = new AppFileOce();
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
