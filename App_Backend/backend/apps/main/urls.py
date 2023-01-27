from django.urls import path, include
from rest_framework.routers import SimpleRouter
from .views import NoteCreateView, DayStatsViewSet

router = SimpleRouter()
router.register('stats', DayStatsViewSet, basename='project')


urlpatterns = [
    path('post_note/', NoteCreateView.as_view()),
    path('days/', include(router.urls))
]
